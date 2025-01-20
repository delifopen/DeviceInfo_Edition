# DeviceInfo Edition


Android library to get device information in a super easy way.
DeviceInfo library gives you details of Hardware & Software configurations of your Android device.
This detail specifications includes information of CPU, RAM, Storage, OS, Sensors, Core, Battery, Data Network, WiFi, SIM, Bluetooth, Display, Supported features, Manufacturer, Installed Apps.


Add this to your project build.gradle
``` gradle
allprojects {
    repositories {
        maven {
            url "https://jitpack.io"
        }
    }
}
```
 
#### Dependency
[![](https://jitpack.io/v/appsfeature/device-info.svg)](https://jitpack.io/#appsfeature/device-info)
```gradle
dependencies {
    implementation 'com.github.appsfeature:device-info:2.03'
    implementation 'io.github.ParkSangGwon:tedpermission-normal:3.4.2'
}
```

#### Usage method
In your activity class:
```java 
      DeviceInfo.getInstance()
              .setEnablePermissionRequiredInfo(true)
              .setDebugMode(true)
              .setPermission(Manifest.permission.ACCESS_FINE_LOCATION)
              .addCallback(new DeviceInfoCallback<DeviceInfoResult>() {
                  @Override
                  public void onSuccess(DeviceInfoResult response) {
                      Log.d("onSuccess",response.toString());
                  }

                  @Override
                  public void onError(Exception e) {
                      Log.d("onError",e.getMessage());
                  }
              });

      // call this method from Main thread.
      DeviceInfo.getInstance().fetch(this);
                       -OR-
      // call this method from Worker thread.
      DIResult result = DeviceInfo.getInstance().fetchEnqueue(this);
```


#### Permissions Required
Add following Permission in your Manifest file if need the following details(Network, SIM, Bluetooth).
## Network
```xml
    Normal Permission
        <uses-permission android:name="android.permission.INTERNET" />
        <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
        <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />

    Runtime Permission
        <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
        <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```
## SIM
```xml
    Runtime Permission
        <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
```
## Bluetooth
```xml
    Normal Permission
        <uses-permission android:name="android.permission.BLUETOOTH"/>
```
## Application
```xml
    Normal Permission
        <uses-permission android:name="android.permission.QUERY_ALL_PACKAGES"
                tools:ignore="QueryAllPackagesPermission" />

        <queries>
            <intent>
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.BROWSABLE" />
                <data android:scheme="https" />
            </intent>
            <intent>
                <action android:name="android.intent.action.VIEW" />
                <!-- If you don't know the MIME type in advance, set "mimeType" to "*/*". -->
                <data android:mimeType="application/pdf" />
            </intent>
            <intent>
                <action android:name="android.intent.action.TTS_SERVICE" />
            </intent>
            <intent>
                <action android:name="android.speech.RecognitionService" />
            </intent>
            <intent>
                <action android:name="android.media.browse.MediaBrowserService" />
            </intent>
            <intent>
                <action android:name="android.intent.action.SENDTO"/>
                <data android:scheme="smsto" android:host="*" />
            </intent>
        </queries>
```


Device Info provides following information of your Android device which grouped as below.

## Android Device Information
```  
UserDeviceInfo.get().getInfo(context)               : HashMap<String, String>
UserDeviceInfo.get().getBuildSDKVersion()           : int
UserDeviceInfo.get().getAndroidOSName()             : String
UserDeviceInfo.get().getOSVersion()                 : String
UserDeviceInfo.get().getBaseOSVersion()             : String
UserDeviceInfo.get().getDisplayVersion()            : String
UserDeviceInfo.get().getBoard()                     : String
UserDeviceInfo.get().getBootloader()                : String
UserDeviceInfo.get().getBuildBrand()                : String
UserDeviceInfo.get().getBuildHost()                 : String
UserDeviceInfo.get().getBuildTags()                 : String
UserDeviceInfo.get().getBuildUser()                 : String
UserDeviceInfo.get().getBuildVersionCodename()      : String
UserDeviceInfo.get().getBuildVersionIncremental()   : String
UserDeviceInfo.get().getBuildVersionRelease()       : String  
UserDeviceInfo.get().getDevice()                    : String
UserDeviceInfo.get().getFingerprint()               : String
UserDeviceInfo.get().getHardware()                  : String
UserDeviceInfo.get().getLanguage()                  : String
UserDeviceInfo.get().getManufacturer()              : String
UserDeviceInfo.get().getModel()                     : String
UserDeviceInfo.get().getDeviceId(context)           : String
UserDeviceInfo.get().getPhoneNo(context)            : String
UserDeviceInfo.get().getProduct()                   : String
UserDeviceInfo.get().getRadioVer()                  : String
UserDeviceInfo.get().getScreenDisplayID(context)    : String
UserDeviceInfo.get().getSerial(context)             : String
UserDeviceInfo.get().getDeviceType(context)         : String
UserDeviceInfo.get().getPhoneType(context)          : String
UserDeviceInfo.get().getPhoneTypeMod(context)       : int
UserDeviceInfo.get().getBuildID()                   : String
UserDeviceInfo.get().getBuildTime()                 : long
UserDeviceInfo.get().getOrientation(context)        : int
UserDeviceInfo.get().isDeviceRooted()               : boolean
UserDeviceInfo.get().isDeveloperModeEnabled(context): boolean
UserDeviceInfo.get().isWifiAdbEnabled(context)      : boolean   
UserDeviceInfo.get().isRunningOnEmulator()          : boolean   
```
## Battery
```
Battery.get().getInfo(context)                      : HashMap<String, String>
Battery.get().getBatteryTechnology(context)         : String
Battery.get().getBatteryTemperature(context)        : float
Battery.get().getBatteryVoltage(context)            : int
Battery.get().getDeviceChargingState(context)       : String
Battery.get().getChargingSource(context)            : String
Battery.get().getChargingSourceIndexDetail(context) : String
Battery.get().isBatteryPresent(context)             : boolean
Battery.get().getBatteryPercentage(context)         : int
Battery.get().getBatteryHealth(context)             : String
```
## Sensor
```
Sensors.get().getAllSensors(context)            : List<Sensor>
```
## CPU
```
CPU.get().getInfo()                             : HashMap<String, String>
CPU.get().getCPUInfo()                          : HashMap<String, String>
CPU.get().getStringSupported32bitABIS()         : String
CPU.get().getStringSupported64bitABIS()         : String
CPU.get().getStringSupportedABIS()              : String
CPU.get().getSupportedABIS()                    : String[]
CPU.get().getNumCores()                         : int 
```
## Network (permission required)
```
Network.get().getInfo(context)                  : HashMap<String, String>
Network.get().getAllCellInfo(context)           : List<CellInfo>
Network.get().loadCellTowerInfo(context)        : String
Network.get().isWifiEnabledDetail(context)      : String
Network.get().getMACAddress(context)            : String
Network.get().getWifiBSSID(context)             : String
Network.get().isNetworkAvailable(context)       : String
Network.get().isInternetConnected(context)      : boolean
Network.get().getIPv4Address(context)           : String
Network.get().getIPv6Address(context)           : String
Network.get().getDataType(context)              : String
Network.get().getWifiSSID(context)              : String
Network.get().getWifiLinkSpeed(context)         : String
```
## SIM (permission required)
```
DeviceSim.get().getInfo(context)                : HashMap<String, String> 
DeviceSim.get().getActiveMultiSimInfo(context)  : List<SubscriptionInfo>
DeviceSim.get().getCarrier(context)             : String
DeviceSim.get().isSimNetworkLocked(context)     : String
DeviceSim.get().getIMEI(context)                : String
DeviceSim.get().getIMSI(context)                : String
DeviceSim.get().getNumberOfActiveSim(context)   : String
DeviceSim.get().getSIMSerial(context)           : String
DeviceSim.get().getSIMSerial(context)           : String
```
## Storage
```
DeviceMemory.get().getInfo(context)                 : HashMap<String, String>
DeviceMemory.get().getTotalInternalMemorySize()     : long 
DeviceMemory.get().getAvailableInternalMemorySize() : long 
DeviceMemory.get().getTotalRAM()                    : long 
DeviceMemory.get().totalRamMemorySize()             : long 
DeviceMemory.get().freeRamMemorySize()              : long 
DeviceMemory.get().externalMemoryAvailable()        : String 
DeviceMemory.get().getTotalExternalMemorySize()     : long 
DeviceMemory.get().getAvailableExternalMemorySize() : long 
DeviceMemory.get().convertToGbFormatted()           : String 
```
## Bluetooth (permission required)
```
Bluetooth.get().getInfo(context)                    : HashMap<String, String> 
Bluetooth.get().hasBluetoothLe(context)             : Boolean
Bluetooth.get().hasBluetoothLeDetail(context)       : String
Bluetooth.get().hasBluetoothLeAdvertising(context)  : String
```
## Display
```
DeviceDisplay.get().getInfo(context)                : HashMap<String, String> 
DeviceDisplay.get().getDisplayObject(context)       : Display 
DeviceDisplay.get().getDensity(context)             : String 
DeviceDisplay.get().getPhysicalSize(context)        : String 
DeviceDisplay.get().getOrientation(context)         : int 
DeviceDisplay.get().getDeviceOrientation(context)   : String 
DeviceDisplay.get().getLayoutDirection(context)     : int 
DeviceDisplay.get().getResolution(context)          : String 
DeviceDisplay.get().isScreenRound(context)          : boolean 
DeviceDisplay.get().getRefreshRate(context)         : String 
DeviceDisplay.get().getDisplayXYCoordinates(context) : int[] 
```
## Features
```
Feature.get().getInfo(context)                      : HashMap<String, String>   
Feature.get().getDeviceList(context)                : HashMap<String, UsbDevice>
Feature.get().getConnectedDevicesList(context)      : String
Feature.get().isNfcEnabled(context)                 : boolean
Feature.get().checkMultiTouchSupport(context)       : boolean
```
## DeviceConfig
```
DeviceConfig.get().getInfo(context)                 : HashMap<String, String>    
DeviceConfig.get().getCurrentDate()                 : String
DeviceConfig.get().getDeviceRingerMode(context)     : String
DeviceConfig.get().getFormattedDate()               : String
DeviceConfig.get().getFormattedTime()               : String
DeviceConfig.get().getFormattedUpTime()             : String
DeviceConfig.get().hasSdCard()                      : String

DeviceConfig.isDeveloperModeEnabled(context)        : boolean
DeviceConfig.isWifiAdbEnabled(context)              : boolean   
DeviceConfig.isDeviceRooted()                       : boolean
DeviceConfig.isRunningOnEmulator()                  : boolean   
```

```json
{
  "applicationInfo": [
    {
      "activities": "org.chrome.browser.document.ChromeLauncherActivity,com.google.android.chrome.TranslateDispatcher",
      "app_installed_date_formatted": "22-02-2023 | 12:02 pm",
      "app_last_modified_date_formatted": "28-04-2024 | 02:55 pm",
      "icon": {
        "mSrcDensityOverride": 0
      },
      "installedDate": 1677047523452,
      "label": "com.google.android.apps.chrome.Main",
      "lastModified": 1714296323330,
      "launchActivity": "com.google.android.apps.chrome.Main",
      "name": "Adblock Browser",
      "packageName": "org.adblockplus.browser",
      "providers": "org.chromium.chrome.browser.util.ChromeFileProvider,org.chrome.browser.download.DownloadFileProvider",
      "reqPermission": "ACCESS_WIFI_STATE, ACCESS_NETWORK_STATE, BLUETOOTH, REORDER_TASKS, FOREGROUND_SERVICE",
      "services": "org.chromium.chrome.browser.photo_picker.DecoderService,org.chrome.download.DownloadForegroundService",
      "version": "3.4.5",
      "versionCode": "2064000053"
    },
    {
      "activities": "com.amazon.mShop.navigation.MainActivity,com.amazon.mShop.splashscreen.StartupActivity",
      "app_installed_date_formatted": "22-02-2023 | 11:53 am",
      "app_last_modified_date_formatted": "04-05-2024 | 02:05 am",
      "icon": {
        "mSrcDensityOverride": 0
      },
      "installedDate": 1677046992068,
      "label": "com.amazon.mShop.home.HomeActivity",
      "lastModified": 1714768546840,
      "launchActivity": "com.amazon.mShop.home.HomeActivity",
      "name": "Amazon",
      "packageName": "in.amazon.mShop.android.shopping",
      "providers": "com.reactnativecommunity.webview.RNCWebViewFileProvider,com.burnweb.rnsendintent.FileProvider",
      "reqPermission": "INSTALL_SHORTCUT, VIBRATE, ACCESS_NETWORK_STATE, INTERNET, RECEIVE_SMS, READ_SMS, SEND_SMS, WAKE_LOCK",
      "services": "com.amazon.identity.auth.device.bootstrapSSO.BootstrapSSOService,com.amazon.service.FFSWhiteListJobService",
      "version": "28.9.0.300",
      "versionCode": "1241271011"
    }
  ],
  "batteryInfo": {
    "charging_source": "Source-AC",
    "charging_source_index": "AC",
    "temperature": "33.0",
    "health": "Battery health Good",
    "technology": "Li-ion",
    "charging_state": "Charging",
    "charged_percentage": "99",
    "is_battery_present": "true",
    "voltage": "4500"
  },
  "bluetoothInfo": {
    "has_bluetooth_le": "YES",
    "has_bluetooth_le_advertising": "YES"
  },
  "cpuInfo": {
    "CPU_revision": "1",
    "num_of_cores": "8",
    "CPU_architecture": "8",
    "supported_ABIS": "arm64-v8a_armeabi-v7a_armeabi",
    "processor": "7",
    "supported_32": "armeabi-v7a_armeabi",
    "BogoMIPS": "38.40",
    "supported_64": "arm64-v8a",
    "CPU_part": "0xd41",
    "Features": "fp asimd evtstrm aes pmull sha1 sha2 crc32 atomics fphp asimdhp cpuid asimdrdm lrcpc dcpop asimddp",
    "CPU_implementer": "0x41",
    "CPU_variant": "0x1",
    "supported_ABIS_details": "[arm64-v8a, armeabi-v7a, armeabi]"
  },
  "deviceConfigInfo": {
    "current_date": "05-05-2024 | 01:24 pm",
    "is_device_rooted": "false",
    "formatted_time": "1:24:19 pm",
    "is_running_on_emulator": "false",
    "formatted_up_time": "3:17:02 am",
    "is_developer_mode_enabled": "true",
    "sd_card_available": "YES",
    "formatted_date": "05-May-2024",
    "device_ringer_mode": "NORMAL",
    "is_wifi_adb_enabled": "false"
  },
  "deviceInfo": {
    "phone_no": "Neither user 10687 nor current process has android.permission.READ_PHONE_STATE",
    "device_unique_fingerprint": "motorola/dubai_g/dubai:13/T1RDS33.116-55-88-5/cf6dc6-fb8h3:user/release-keys",
    "android_base_os_version_name": "motorola/dubai_g/dubai:13/T1RD33.146-44-88/441420-2d5he8:user/release-keys",
    "device_model": "motorola_edge_30",
    "build_board": "dubai",
    "version_release": "13",
    "build_time": "20-03-2024 | 09:55 pm",
    "build_user": "hudsoncm",
    "language": "en",
    "device_type": "PHONE",
    "phone_type_mod": "GSM",
    "manufacturer": "motorola",
    "build_device": "dubai",
    "host": "iscublg239",
    "radio_version": "M7334_HI455_35.41.08.87R DUBAI_ROWDSDS_PVT_CUST",
    "hardware": "qcom",
    "product": "dubai_g",
    "phone_type": "GSM",
    "orientation": "PORTRAIT",
    "is_device_rooted": "false",
    "screen_display_id": "0",
    "version_incremental": "cx7dc6-fb4k3",
    "brand_name": "motorola",
    "build_version_sdk": "33",
    "serial": "getSerial: The uid 10687 does not meet the requirements to access device identifiers.",
    "build_id": "T1RDS37.116-66-18-8",
    "display_version": "T1RDS37.116-66-18-8",
    "android_os_version_name": "13",
    "android_os_name": "Android 13(Tiramisu)",
    "version_code_name": "REL",
    "device_unique_id": "346e22d171ff04be",
    "system_boot_loader_version": "MBM-3.0-dubai_g-1dr3ts7k46-240720",
    "build_tags": "release-keys"
  },
  "deviceMemoryInfo": {
    "total_internal_memory_size": "108.34862 Gb",
    "total_ram_memory_size": "7.209423 Gb",
    "available_internal_memory_size": "13.472279 Gb",
    "available_external_memory_size": "13.472279 Gb",
    "external_memory_available": "YES",
    "total_external_memory_size": "108.34862 Gb",
    "total_ram": "7.209423 Gb",
    "free_ram_memory_size": "2.9585266 Gb"
  },
  "deviceSimInfo": {
    "country": "in",
    "carrier": "jio_4g",
    "IMSI": "getSubscriberIdForSubscriber: The uid 10687 does not meet the requirements to access device identifiers.",
    "IMEI": "getImeiForSlot: The uid 10687 does not meet the requirements to access device identifiers.",
    "number_of_active_sim": "1",
    "sim_subscription": "[{\"carrier_name\":\"JIO 4G\",\"country_iso\":\"in\",\"display_name\":\"JIO Main\",\"icc_id\":\"\",\"mcc\":405,\"number\":\"\",\"sim_slot_index\":0,\"subscription_id\":4}]",
    "sim_network_locked": "NO",
    "sim_serial": "getIccSerialNumber: The uid 10687 does not meet the requirements to access device identifiers."
  },
  "displayInfo": {
    "orientation": "portrait",
    "density": "XMHDPI",
    "screen_round": "false",
    "layout_direction": "0",
    "refresh_rate": "90.0",
    "physical_size": "6.1825423",
    "resolution": "2235x1080"
  },
  "networkInfo": {
    "wifi_enabled": "YES",
    "cell_tower": "{\"signal_strength\":\"-98\",\"gsm_cell_identity\":\"1372177\",\"mobile_country_code\":\"405\",\"mobile_network_code\":\"872\",\"location_area_code\":\"unknown\"}",
    "mac_address": "",
    "bssid": "55:0c:88:00:4a:d9",
    "connection_status": "Connected",
    "ip_v4_address": "192.0.3.4",
    "ip_v6_address": "2409:4446:6567:6CBD:D:7666:FE57:F687",
    "data_type": "WiFi",
    "is_internet_connected": "true",
    "link_speed": "780 Mbps",
    "ssid": "\"AKR WiFi.5G\""
  },
  "featureInfo": {
    "multi_touch": "Supported",
    "is_nfc_enabled": "false",
    "connected_devices_list": "No device connected"
  },
  "sensorsInfo": [
    {
      "mFlags": 2432
    }
  ]
}
```


## ChangeLog

#### Version 2.03:
* Initial build
* fixed some errors
* update gradle build
* change build tedpermission library to io.github.ParkSangGwon:tedpermission-normal:3.4.2
* fixed tedpermission library errors
* remastered old version 
* compileSdkVersion 30, Java Version 1.8

## Thanks for appsfeature to provide the source
