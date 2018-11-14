package com.octousa.sensors.manager

import android.app.NotificationManager
import android.app.PendingIntent
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
import com.octousa.sensors.R
import com.octousa.sensors.utils.SensorsConstants
import com.octousa.sensors.utils.SensorsConstants.DURATION


/**
 * Created by taoufik.kassour on 6/29/18/ 14
 */
internal object SensorsAlerts {

    fun alertDialog(context: Context, action: String,  title: String, message: String){
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Enable", { _, _-> context.startActivity(Intent(action)) })
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", { dialog, _ -> dialog.dismiss();  })

         alertDialog.show()
    }

    fun simpleAlert(context: Context, title: String, message: String){
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.show()
    }

    fun pushNotification(context: Context, action: String, title: String, message: String, notificationId: Int ){
        val intent =  Intent(action)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val mBuilder = NotificationCompat.Builder(context, "_")
                .setSmallIcon(R.drawable.ic_check)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)

        NotificationManagerCompat.from(context).notify(notificationId, mBuilder.build())
    }

    fun snackBarAlert(context: Context, view : View, action: String, message: String){
        (view.findViewById(android.support.design.R.id.snackbar_text) as TextView)
                .setTextColor(Color.WHITE)
        Snackbar.make(view, message, DURATION)
                .setAction("Enable", {context.startActivity(Intent(action))})
                .show()
    }

    fun simpleSnackBarAlert(context: Context, view : View, message: String){
        (view.findViewById(android.support.design.R.id.snackbar_text) as TextView)
                .setTextColor(Color.WHITE)
        Snackbar.make(view, message, DURATION).show()

    }

    fun clearPushNotification(context: Context, notificationId: Int) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(notificationId)
    }
}