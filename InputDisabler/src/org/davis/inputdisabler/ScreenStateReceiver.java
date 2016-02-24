package org.davis.inputdisabler;

/*
 * Created by Dāvis Mālnieks on 04/10/2015
 * 	Some updates/fixes on 23/02/2016 and 24/02/2016
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import org.davis.inputdisabler.utils.Constants;
import org.davis.inputdisabler.utils.Device;

import static android.provider.Settings.Secure.DOUBLE_TAP_TO_WAKE;

public class ScreenStateReceiver extends BroadcastReceiver implements SensorEventListener {

    public static final String TAG = "ScreenStateReceiver";

    public static final boolean DEBUG = false;

    android.os.Handler mDozeDisable;

    static boolean mScreenOn;

    SensorManager mSensorManager;

    Sensor mSensor;

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent == null) return;

        if(DEBUG)
            Log.d(TAG, "Received intent: " + intent.getAction());

        int isDoubleTapEnabled = Settings.Secure.getInt(context.getContentResolver(),
                DOUBLE_TAP_TO_WAKE, 0);

        if(DEBUG)
            Log.d(TAG, "Double tap to wake is " +
                    ((isDoubleTapEnabled != 0) ? "" : "not ") + "enabled");

        switch (intent.getAction()) {
            case Intent.ACTION_SCREEN_ON:
                if(DEBUG)
                    Log.d(TAG, "Screen on!");

                // Perform enable->disable->enable sequence
                handleScreenOn();
                break;
            case Intent.ACTION_SCREEN_OFF:
                if(DEBUG)
                    Log.d(TAG, "Screen off!");

                // Don't turn off touch when double tap is enabled
                if(isDoubleTapEnabled != 0) {
                    Device.enableKeys(false);
                } else {
                    Device.enableDevices(false);
                }

                // Screen is now off
                mScreenOn = false;
                break;
            case Constants.ACTION_DOZE_PULSE_STARTING:
                if(DEBUG)
                    Log.d(TAG, "Doze");

                mDozeDisable = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        if(!mScreenOn) {
                            if(DEBUG)
                                Log.d(TAG, "Screen was turned off while dozing");

                            Device.enableKeys(false);
                        } else {
                            if(DEBUG)
                                Log.d(TAG, "Screen was turned on while dozing");

                            handleScreenOn();
                        }
                    }
                };
                mDozeDisable.postDelayed(runnable, Constants.DOZING_TIME);

                // Don't enable touch keys when dozing
                // Perform enable->disable->enable sequence
                handleScreenOn();
                break;
            case TelephonyManager.ACTION_PHONE_STATE_CHANGED:
                if(DEBUG)
                    Log.d(TAG, "Phone state changed!");

                final TelephonyManager telephonyManager =
                        (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

                switch (telephonyManager.getCallState()) {
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
                        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
                        mSensorManager.registerListener(this, mSensor, 3);
                        break;
                    case TelephonyManager.CALL_STATE_IDLE:
                        if(mSensorManager != null) {
                            mSensorManager.unregisterListener(this);
                        }

			            if(!mScreenOn) {
                            handleScreenOn();
			            }
                        break;
                }
                break;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.values[0] == 0.0f) {
            if(DEBUG)
                Log.d(TAG, "Proximity: screen off");

            Device.enableDevices(false);

	        // Screen is off now
	        mScreenOn = false;
        } else {
            if(DEBUG)
                Log.d(TAG, "Proximity: screen on");

            handleScreenOn();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    // Handles screen on
    private void handleScreenOn() {
        // Enable keys
        Device.enableKeys(true);

        // Perform enable->disable->enable sequence
        Device.enableTouch(true);
        Device.enableTouch(false);
        Device.enableTouch(true);

        // Screen is now on
        mScreenOn = true;
    }

}
