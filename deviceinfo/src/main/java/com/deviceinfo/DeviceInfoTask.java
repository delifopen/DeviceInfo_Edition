package com.deviceinfo;

import android.content.Context;

import com.deviceinfo.application.Application;
import com.deviceinfo.battery.Battery;
import com.deviceinfo.config.DeviceConfig;
import com.deviceinfo.cpu.CPU;
import com.deviceinfo.device.UserDeviceInfo;
import com.deviceinfo.display.DeviceDisplay;
import com.deviceinfo.features.Feature;
import com.deviceinfo.interfaces.DeviceInfoCallback;
import com.deviceinfo.memory.DeviceMemory;
import com.deviceinfo.model.DeviceInfoResult;
import com.deviceinfo.permission.bluetooth.Bluetooth;
import com.deviceinfo.permission.network.Network;
import com.deviceinfo.permission.sim.DeviceSim;
import com.deviceinfo.sensors.Sensors;
import com.deviceinfo.util.DIResult;
import com.deviceinfo.util.DITaskRunner;

import java.util.concurrent.Callable;

public class DeviceInfoTask {

    private final DeviceInfoCallback<DeviceInfoResult> callback;

    public DeviceInfoTask() {
        this(null);
    }

    public DeviceInfoTask(DeviceInfoCallback<DeviceInfoResult> callback) {
        this.callback = callback;
    }

    protected DIResult doInBackground(Context context) {

        try {
            //permission not required
            DeviceInfoResult jsonResponse = new DeviceInfoResult()
                    .setDeviceInfo(UserDeviceInfo.get().getInfo(context))
                    .setBatteryInfo(Battery.get().getInfo(context))
                    .setDisplayInfo(DeviceDisplay.get().getInfo(context))
                    .setCpuInfo(CPU.get().getInfo())
                    .setFeatureInfo(Feature.get().getInfo(context))
                    .setDeviceConfigInfo(DeviceConfig.get().getInfo(context))
                    .setDeviceMemoryInfo(DeviceMemory.get().getInfo(context));

            if (DeviceInfo.getInstance().isEnableSensorInfo) {
                jsonResponse.setSensorsInfo(Sensors.get().getInfo(context));
            }
            if (DeviceInfo.getInstance().isEnableApplicationInfo) {
                jsonResponse.setApplicationInfo(new Application().getInfo(context));
            }

            //permission required
            if(DeviceInfo.getInstance().isEnablePermissionRequiredInfo) {
                if (DeviceInfo.getInstance().isEnableBluetoothInfo) {
                    jsonResponse.setBluetoothInfo(Bluetooth.get().getInfo(context));
                }
                if (DeviceInfo.getInstance().isEnableNetworkInfo) {
                    jsonResponse.setNetworkInfo(Network.get().getInfo(context));
                }
                if (DeviceInfo.getInstance().isEnableSIMInfo) {
                    jsonResponse.setDeviceSimInfo(DeviceSim.get().getInfo(context));
                }
            }

            return new DIResult(jsonResponse);
        } catch (Exception e) {
            return new DIResult(e);
        }
    }

    protected void onPostExecute(DIResult result) {
        if(callback != null) {
            if (result.getException() != null) {
                callback.onError(result.getException());
            } else {
                callback.onSuccess(result.getResult());
            }
        }
    }

    public void execute(Context context){
        DITaskRunner.getInstance().executeAsync(() -> doInBackground(context), this::onPostExecute);
    }

}
