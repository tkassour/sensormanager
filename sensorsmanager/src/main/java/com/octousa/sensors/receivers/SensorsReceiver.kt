package com.octousa.sensors.receivers

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.octousa.sensors.handlers.BatterySensor
import com.octousa.sensors.handlers.BluetoothSensor.bluetoothStatusNotification
import com.octousa.sensors.handlers.LocationSensor.gpsLocationNotification
import com.octousa.sensors.handlers.LocationSensor.isServiceLocationOn
import com.octousa.sensors.manager.SensorsAlerts.clearPushNotification
import com.octousa.sensors.utils.SensorsConstants.BLUETOOTH_ID
import com.octousa.sensors.utils.SensorsConstants.GPS_ID


/**
 * Created by taoufik.kassour on 6/29/18
 *
 */
class SensorsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        if(intent == null || context == null) return

        if(intent.action.equals("android.location.PROVIDERS_CHANGED")) { //Todo: it is fired twice due to multiple BroadcastRegister
            if(!isServiceLocationOn(context))
                gpsLocationNotification(context)
            else
                clearPushNotification(context, GPS_ID)

        } else if (intent.action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
            val state: Int = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)

            if(state == BluetoothAdapter.STATE_OFF)
                bluetoothStatusNotification(context)
            if(state == BluetoothAdapter.STATE_ON)
                clearPushNotification(context, BLUETOOTH_ID)

        } else if(intent.action.equals("android.intent.action.BATTERY_LOW")) {
            BatterySensor.lowBatteryPushNotification(context)
        }
    }
}
