package com.octousa.sensors.utils

/**
 * Created by taoufik.kassour on 7/2/18/ 16
 */
object  SensorsConstants {

    //Notification IDs
    const val BLUETOOTH_ID = 100
    const val GPS_ID = 101
    const val LOW_BATTERY_ID = 102

    //Snackbar Duration Alert
    const val DURATION = 8000

    //Sensors
    const val GPS_LOCATION = "GPS LOCATION"
    const val BLUETOOTH = "BLUETOOTH"
    const val POWER_SAVING_MODE = "POWER SAVING MODE"
    const val LOW_BATTERY = "LOW BATTERY"
    const val DATA_AVAILABILITY = "DATA AVAILABILITY"
    const val CALL_PERMISSION = "CALL PERMISSION STATUS"

    const val SENSORS_MAX = 6 // Number of sensors to check

    //Sensor Description
    private const val GPS_LOCATION_DESC= "Location service is used for GPS Location coordinates"
    private const val BLUETOOTH_DESC = "BLE for TAG Connectivity"
    private const val POWER_SAVING_MODE_DESC = "POWER SAVING MODE DESC"
    private const val LOW_BATTERY_DESC = "Glimpse might not record trip on low battery level"
    private const val DATA_AVAILABILITY_DESC = "DATA AVAILABILITY DESC"
    private const val CALL_PERMISSION_DESC = "CALL PERMISSION STATUS DESC"

    fun getSensorDesc(sensor: String): String {
        return when(sensor){
            GPS_LOCATION -> GPS_LOCATION_DESC
            BLUETOOTH -> BLUETOOTH_DESC
            POWER_SAVING_MODE -> POWER_SAVING_MODE_DESC
            LOW_BATTERY -> LOW_BATTERY_DESC
            DATA_AVAILABILITY -> DATA_AVAILABILITY_DESC
            CALL_PERMISSION -> CALL_PERMISSION_DESC
            else -> "Invalid Sensor Description"
        }
    }
}