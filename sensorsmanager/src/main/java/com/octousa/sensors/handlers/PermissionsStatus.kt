package com.octousa.sensors.handlers

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager

/**
 * Created by taoufik.kassour on 11/5/18/ 08
 */
internal object PermissionsStatus {

     fun callPermissionStatus(context: Context): Boolean {
        val result = context.checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_STATE)
        return result == PackageManager.PERMISSION_GRANTED
    }
}