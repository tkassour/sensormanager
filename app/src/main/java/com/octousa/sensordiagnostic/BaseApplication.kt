package com.octousa.sensordiagnostic

import android.app.Application
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication

/**
 * Created by taoufik.kassour on 10/24/18/ 18
 */
class BaseApplication :  MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
    }

}