package com.octousa.sensors.manager

import android.content.Context
import android.view.View
import com.octousa.sensors.handlers.BatterySensor
import com.octousa.sensors.handlers.BatterySensor.isBatteryLow
import com.octousa.sensors.handlers.BatterySensor.isPowerSaveModeOn
import com.octousa.sensors.handlers.BluetoothSensor
import com.octousa.sensors.handlers.BluetoothSensor.isBluetoothOn
import com.octousa.sensors.handlers.LocationSensor.gpsLocationAlert
import com.octousa.sensors.handlers.LocationSensor.isServiceLocationOn
import com.octousa.sensors.handlers.NetworkSensor.isDataAvailable
import com.octousa.sensors.handlers.PermissionsStatus.callPermissionStatus
import com.octousa.sensors.model.Sensor
import com.octousa.sensors.utils.SensorsConstants.BLUETOOTH
import com.octousa.sensors.utils.SensorsConstants.CALL_PERMISSION
import com.octousa.sensors.utils.SensorsConstants.DATA_AVAILABILITY
import com.octousa.sensors.utils.SensorsConstants.GPS_LOCATION
import com.octousa.sensors.utils.SensorsConstants.LOW_BATTERY
import com.octousa.sensors.utils.SensorsConstants.POWER_SAVING_MODE
import com.octousa.sensors.utils.SensorsConstants.SENSORS_MAX
import com.octousa.sensors.utils.Type


/** Created by taoufik.kassour on 6/28/18 */

open class SensorManager(val context: Context) {

    var sensors = ArrayList<Sensor>()

    /**
     *  Initialize Sensor Detection
     *  Must provide sensor type from SensorsConstants class as input to get started
     *
     *  @param sensorsList list of sensor to check for
     */
    fun init(@Type vararg sensorsList: String){
        if(sensorsList.size > SENSORS_MAX)
            throw IllegalArgumentException("Invalid number of input arguments: Max number of Sensors to check for is $SENSORS_MAX")

        if(sensorsList.isEmpty())
            throw IllegalArgumentException("Must have at least one Sensor to check for")

        sensorsList.forEach { sensors.add(Sensor(it, false))}
    }


    /**
     * This method returns a list of Sensor Object with their current status
     * It can be passed to the SensorsStatusView
     *
     * @param context Activity/Fragment view context (parent)git b
     * @return List of Sensor Status
     */
    fun getSensorsStatus() : ArrayList<Sensor>{

        for(sensor in sensors){
            when(sensor.name) {
                GPS_LOCATION -> sensor.status = isServiceLocationOn(context)
                BLUETOOTH -> sensor.status = isBluetoothOn()
                POWER_SAVING_MODE -> sensor.status = isPowerSaveModeOn(context)
                LOW_BATTERY -> sensor.status = isBatteryLow(context)
                DATA_AVAILABILITY -> sensor.status = isDataAvailable(context)
                CALL_PERMISSION -> sensor.status = callPermissionStatus(context)
            }
        }

        return sensors
    }


    /**
     *  This method returns false if any of the sensors fail to meet the desired status
     *  And shows Alert Dialog
     *
     * @param context parent context
     * @return sensors status
     */
    fun checkSensors() : Boolean{
        sensors.forEach {
            when(it.name) {
                GPS_LOCATION ->  {
                    if(!isServiceLocationOn(context)) {
                        gpsLocationAlert(context)
                        return false
                    }
                }
                BLUETOOTH -> {
                    if(!isBluetoothOn()) {
                        BluetoothSensor.bluetoothAlert(context)
                        return false
                    }
                }
                POWER_SAVING_MODE -> {
                    if(!isPowerSaveModeOn(context))
                        return false
                }
                LOW_BATTERY -> {
                    if(!isBatteryLow(context)) {
                        BatterySensor.lowBatteryAlert(context)
                        return false
                    }
                }
                DATA_AVAILABILITY -> {
                    if(!isDataAvailable(context))
                        return false
                }
                CALL_PERMISSION -> {
                    if(!callPermissionStatus(context))
                        return false
                }
            }
        }

        return true
    }


    /**
     *  This method returns false if any of the sensors fail to meet the desired status
     *  And shows SnackBar Alert
     *
     * @param context parent context
     * @param view Activity/Fragment view
     * @return sensors status
     */
    fun checkSensors(view: View):Boolean {
        for(it in sensors) {
            when(it.name) {
                GPS_LOCATION ->  {
                    if(!isServiceLocationOn(context)) {
                        gpsLocationAlert(context, view)
                        return false
                    }
                }
                BLUETOOTH -> {
                    if(!isBluetoothOn()) {
                        BluetoothSensor.bluetoothAlert(context, view)
                        return false
                    }
                }
                POWER_SAVING_MODE -> {
                    if(!isPowerSaveModeOn(context))
                        return false
                }
                LOW_BATTERY -> {
                    if(isBatteryLow(context)) {
                        BatterySensor.lowBatteryAlert(context, view)
                        return false
                    }
                }
                DATA_AVAILABILITY -> {
                    if(!isDataAvailable(context))
                        return false
                }
                CALL_PERMISSION -> {
                    if(callPermissionStatus(context))
                        return false
                }
            }
        }

        return true
    }

}
