package com.octousa.sensors.handlers

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by taoufik.kassour on 11/5/18
 */

object NetworkSensor {

    fun getNetworkType(context: Context): NetworkInfo? {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo
    }

    fun isDataAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo

        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI)
                return true   // connected to wifi
             else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
                return true   // connected to the mobile provider's data plan
        } else {
            return false
        }

        return false
    }
}