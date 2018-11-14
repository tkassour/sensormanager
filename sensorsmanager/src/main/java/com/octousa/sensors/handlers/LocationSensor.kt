package com.octousa.sensors.handlers

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.location.LocationManager
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.TextView
import com.octousa.sensors.manager.SensorsAlerts
import com.octousa.sensors.manager.SensorsAlerts.pushNotification
import com.octousa.sensors.utils.SensorsConstants
import com.octousa.sensors.utils.SensorsConstants.DURATION

/**
 * Created by taoufik.kassour on 11/5/18
 */
internal object LocationSensor {

    fun isServiceLocationOn(context: Context) : Boolean {
        val manager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if(manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && getLocationMode(context) == Settings.Secure.LOCATION_MODE_HIGH_ACCURACY)
            return true

        return false
    }

    private fun getLocationMode(context: Context) : Int {
        return Settings.Secure.getInt(context.contentResolver, Settings.Secure.LOCATION_MODE)
    }

    // Alerts
    fun gpsLocationAlert(context: Context) {
        SensorsAlerts.alertDialog(context, Settings.ACTION_LOCATION_SOURCE_SETTINGS,
                "Please enable GPS Location", "Make sure the GPS Mode is set to High accuracy" )
    }

    fun gpsLocationAlert(context: Context, view : View) {
        SensorsAlerts.snackBarAlert(context, view, Settings.ACTION_LOCATION_SOURCE_SETTINGS, "Please enable GPS Location")
    }

    fun gpsLocationNotification(context: Context) {
        pushNotification(context, Settings.ACTION_LOCATION_SOURCE_SETTINGS,
                "Vantage requires GPS", "Please Tap to enable GPS Location to High Accuracy", SensorsConstants.GPS_ID)
    }
}