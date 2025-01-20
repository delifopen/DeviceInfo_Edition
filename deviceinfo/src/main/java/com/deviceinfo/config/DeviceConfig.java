package com.deviceinfo.config;

import static android.media.AudioManager.RINGER_MODE_NORMAL;
import static android.media.AudioManager.RINGER_MODE_SILENT;
import static android.media.AudioManager.RINGER_MODE_VIBRATE;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.Settings;

import com.deviceinfo.util.DILogger;
import com.deviceinfo.util.DITimeLogger;
import com.deviceinfo.util.DIUtility;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class DeviceConfig {

    private static volatile DeviceConfig instance;

    /**
     * Instantiates a new Easy config mod.
     */
    public static DeviceConfig get() {
        if (instance == null) {
            synchronized (DeviceConfig.class) {
                if (instance == null) instance = new DeviceConfig();
            }
        }
        return instance;
    }

    private DeviceConfig() {
    }

    public HashMap<String, String> getInfo(Context context) {
        long startTime = DITimeLogger.getStartTime();
        HashMap<String, String> info = new HashMap<>();
        try {
            info.put("current_date",getCurrentDate());
            info.put("device_ringer_mode", getDeviceRingerMode(context));
            info.put("formatted_date", getFormattedDate());
            info.put("formatted_time", getFormattedTime());
            info.put("formatted_up_time", getFormattedUpTime());
            info.put("sd_card_available", hasSdCard());
            info.put("is_running_on_emulator", String.valueOf(isRunningOnEmulator()));
            info.put("is_device_rooted", String.valueOf(isDeviceRooted()));
            info.put("is_developer_mode_enabled", String.valueOf(isDeveloperModeEnabled(context)));
            info.put("is_wifi_adb_enabled", String.valueOf(isWifiAdbEnabled(context)));
        } catch (Exception e) {
            DILogger.e(e.toString());
            info.put("exception", e.toString());
        }
        DITimeLogger.timeLogging("Config", startTime);
        return info;
    }

    /**
     * Gets date from milliseconds
     */
    public final String getCurrentDate() {
        return DIUtility.formattedDate(System.currentTimeMillis());
    }

    /**
     * Gets Device Ringer Mode.
     */
    @RingerMode
    public final String getDeviceRingerMode(Context context) {
        String ringerMode = RingerMode.NORMAL;
        final AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager != null) {
            switch (audioManager.getRingerMode()) {
                case RINGER_MODE_NORMAL:
                    ringerMode = RingerMode.NORMAL;
                    break;
                case RINGER_MODE_SILENT:
                    ringerMode = RingerMode.SILENT;
                    break;
                case RINGER_MODE_VIBRATE:
                    ringerMode = RingerMode.VIBRATE;
                    break;
                default:
                    //do nothing
                    break;
            }
        }

        return ringerMode;
    }

    /**
     * Gets formatted date.
     */
    public final String getFormattedDate() {
        final DateFormat dateInstance = SimpleDateFormat.getDateInstance();
        return dateInstance.format(Calendar.getInstance().getTime());
    }

    /**
     * Gets formatted time.
     */
    public final String getFormattedTime() {
        final DateFormat timeInstance = SimpleDateFormat.getTimeInstance();
        return timeInstance.format(Calendar.getInstance().getTime());
    }

    /**
     * Gets formatted up time.
     */
    public final String getFormattedUpTime() {
        final DateFormat timeInstance = SimpleDateFormat.getTimeInstance();
        return timeInstance.format(Long.valueOf(SystemClock.uptimeMillis()));
    }

    /**
     * Gets time.
     */
    public final long getTime() {
        return System.currentTimeMillis();
    }

    /**
     * Gets up time.
     */
    public final long getUpTime() {
        return SystemClock.uptimeMillis();
    }

    /**
     * Checks if the device has sd card
     */
    public final String hasSdCard() {
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            return DIUtility.YES;
        }else {
            return DIUtility.NO;
        }
    }

    public final boolean hasSdCard(Context context) {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * Is running on emulator boolean.
     */
    public static boolean isRunningOnEmulator() {
        final boolean isGenyMotion = Build.MANUFACTURER.contains("Genymotion")
                || Build.PRODUCT.contains("vbox86p")
                || Build.DEVICE.contains("vbox86p")
                || Build.HARDWARE.contains("vbox86");
        final boolean isGenericEmulator = Build.BRAND.contains("generic")
                || Build.DEVICE.contains("generic")
                || Build.PRODUCT.contains("sdk")
                || Build.HARDWARE.contains("goldfish");

        return isGenericEmulator || isGenyMotion;
    }

    public static boolean isDeviceRooted() {
        final String su = "su";
        final String[] locations = {
                "/sbin/", "/system/bin/", "/system/xbin/", "/system/sd/xbin/", "/system/bin/failsafe/",
                "/data/local/xbin/", "/data/local/bin/", "/data/local/"
        };
        for (final String location : locations) {
            if (new File(location + su).exists()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Is Developer Mode enabled
     *
     * @return the boolean
     */
    public static boolean isDeveloperModeEnabled(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), Settings.Global.ADB_ENABLED, 0) == 1;
//        return Settings.Secure.getInt(context.getContentResolver(), Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) == 1;
    }

    public static boolean isWifiAdbEnabled(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "adb_wifi_enabled", 0) != 0;
    }
}

