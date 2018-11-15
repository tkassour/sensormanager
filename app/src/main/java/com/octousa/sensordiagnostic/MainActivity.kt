package com.octousa.sensordiagnostic

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.octousa.sensors.manager.SensorManager
import com.octousa.sensors.extensions.setData
import com.octousa.sensors.utils.SensorsConstants.BLUETOOTH
import com.octousa.sensors.utils.SensorsConstants.CALL_PERMISSION
import com.octousa.sensors.utils.SensorsConstants.GPS_LOCATION
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sensorManager = SensorManager(this)
        sensorManager.init(GPS_LOCATION, BLUETOOTH, CALL_PERMISSION)

        sensorManager.getSensorsStatus().also {
           sensorsStatusView.setData(it)
        }

        //sensorManager.checkSensors()
        //sensorManager.checkSensors( main_activity_layout)
    }
}
