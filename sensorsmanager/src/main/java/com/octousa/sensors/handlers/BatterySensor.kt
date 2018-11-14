package com.octousa.sensors.handlers

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.BatteryManager
import android.os.PowerManager
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.TextView
import com.octousa.sensors.manager.SensorsAlerts
import com.octousa.sensors.utils.SensorsConstants
import com.octousa.sensors.utils.SensorsConstants.LOW_BATTERY_ID

/**
 * Created by taoufik.kassour on 11/5/18
 */
internal object BatterySensor {

    fun isPowerSaveModeOn(context: Context) : Boolean{
        val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        return powerManager.isPowerSaveMode
    }

    fun isBatteryLow(context: Context) : Boolean {
        val bm = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        val batLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)

        if(batLevel < 20) return false //to flag as an alert icon

        return true
    }

    fun powerSaveModeAlert(context: Context, view : View){
        val sb = Snackbar.make(view, "Please disable Power Save Mode", SensorsConstants.DURATION)
        val v = sb.view
        val tv = v.findViewById(android.support.design.R.id.snackbar_text) as TextView
        tv.setTextColor(Color.WHITE)

        sb.setAction("Enable", {context.startActivity(Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS)) })
        sb.show()
    }

    // Alerts
    fun lowBatteryAlert(context: Context) {
        SensorsAlerts.simpleAlert(context, "Low Battery", "Please make sure your phone is charged in order to record trips")
    }

    fun lowBatteryAlert(context: Context, view : View) {
        SensorsAlerts.snackBarAlert(context, view, Settings.ACTION_LOCATION_SOURCE_SETTINGS, "Low Battery")
    }

    fun lowBatteryPushNotification(context: Context) {
        SensorsAlerts.pushNotification(context, Settings.ACTION_LOCATION_SOURCE_SETTINGS,
                "Low Battery", "Please make sure your phone is charged in order to record trips", LOW_BATTERY_ID)
    }

}