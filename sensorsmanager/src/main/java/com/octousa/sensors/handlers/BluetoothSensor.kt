package com.octousa.sensors.handlers

import android.app.PendingIntent
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.TextView
import com.octousa.sensors.utils.SensorsConstants

/**
 * Created by taoufik.kassour on 11/5/18/ 08
 */
internal object BluetoothSensor {

    fun isBluetoothOn(): Boolean {
        val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        if(mBluetoothAdapter != null)
            return mBluetoothAdapter.isEnabled

        return false
    }


    //Alerts

    fun bluetoothAlert(context: Context)  {
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setTitle("Please enable Bluetooth")
        alertDialog.setMessage("")
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Enable", { _ , _ ->
            context.startActivity( Intent(Settings.ACTION_BLUETOOTH_SETTINGS))
        })

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", { dialog, which -> dialog.dismiss();  })

        return alertDialog.show()
    }

    fun bluetoothAlert(context: Context, view : View){ //Snack Bar Alert
        val sb = Snackbar.make(view, "Please enable Bluetooth", SensorsConstants.DURATION)
        val v = sb.view
        val tv = v.findViewById(android.support.design.R.id.snackbar_text) as TextView
        tv.setTextColor(Color.WHITE)

        sb.setAction("Enable", {v ->  context.startActivity( Intent(Settings.ACTION_BLUETOOTH_SETTINGS))})
        sb.show()
    }

    fun bluetoothStatusNotification(context: Context){
        val intent =  Intent(Settings.ACTION_BLUETOOTH_SETTINGS)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val mBuilder = NotificationCompat.Builder(context)
                //.setSmallIcon(icon)
                .setContentTitle("Vantage requires Bluetooth")
                .setContentText("Please Tap to enable Bluetooth")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        NotificationManagerCompat.from(context).notify(SensorsConstants.BLUETOOTH_ID, mBuilder.build())
    }
}