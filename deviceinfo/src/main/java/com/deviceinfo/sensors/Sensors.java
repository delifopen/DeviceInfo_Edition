package com.deviceinfo.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import com.deviceinfo.util.DITimeLogger;

import java.util.ArrayList;
import java.util.List;

public class Sensors {

    private static volatile Sensors instance;

    public static Sensors get() {
        if (instance == null) {
            synchronized (Sensors.class) {
                if (instance == null) instance = new Sensors();
            }
        }
        return instance;
    }

    private Sensors() {
    }

    public List<Sensor> getInfo(Context context) {
        long startTime = DITimeLogger.getStartTime();
        List<Sensor> sensors = getAllSensors(context);
        DITimeLogger.timeLogging("Sensors", startTime);
        return sensors;
    }

    /**
     * Gets all sensors.
     */
    public List<Sensor> getAllSensors(Context context) {
        try {
            SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            return sensorManager.getSensorList(Sensor.TYPE_ALL);
        }catch (NullPointerException e){
            return new ArrayList<>();
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
}
