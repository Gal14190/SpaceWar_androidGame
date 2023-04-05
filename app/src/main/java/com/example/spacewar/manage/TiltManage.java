package com.example.spacewar.manage;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TiltManage implements SensorEventListener {
    private CallbackComponent callbackComponent;

    private SensorManager sensorManager;
    private Sensor magneticSensor;

    private float x_value;
    private float y_value;

    private final float DELTA_X = 10;
    private final float DELTA_Y = 5;

    public TiltManage(Context context) {
        x_value = y_value = 0; // reset first values

        // start sensors services
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void setCallbackComponent(CallbackComponent callbackComponent) {
        this.callbackComponent = callbackComponent;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            // get sensor values
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            // Handle MAGNETIC FIELD events
            // roll - move component
            float delta = x - x_value;
            if(Math.abs(delta) > DELTA_X && delta > 0) {
                x_value = x;
                callbackComponent.moveRight();
            } else if(Math.abs(delta) > DELTA_X && delta < 0) {
                x_value = x;
                callbackComponent.moveLeft();
            }

            // pitch - change speed of obstacles
            delta = y - y_value;
            if(Math.abs(delta) > DELTA_Y && delta > 0) {
                y_value = y;
                callbackComponent.speedFaster();
            } else if(Math.abs(delta) > DELTA_Y && delta < 0) {
                y_value = y;
                callbackComponent.speedSlower();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

