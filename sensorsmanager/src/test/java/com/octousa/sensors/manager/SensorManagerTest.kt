package com.octousa.sensors.manager

import android.content.Context
import com.octousa.sensors.handlers.*
import com.octousa.sensors.model.Sensor
import com.octousa.sensors.utils.SensorsConstants
import com.octousa.sensors.utils.SensorsConstants.BLUETOOTH
import com.octousa.sensors.utils.SensorsConstants.CALL_PERMISSION
import com.octousa.sensors.utils.SensorsConstants.DATA_AVAILABILITY
import com.octousa.sensors.utils.SensorsConstants.GPS_LOCATION
import com.octousa.sensors.utils.SensorsConstants.LOW_BATTERY
import com.octousa.sensors.utils.SensorsConstants.POWER_SAVING_MODE
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by taoufik.kassour on 11/13/18
 */

class SensorManagerTest {


    private lateinit var sensorManager : SensorManager
    var context  = mockk<Context>()

    @Before
    fun setup(){
        mockkObject(PermissionsStatus)
        mockkObject(BatterySensor)
        mockkObject(BluetoothSensor)
        mockkObject(LocationSensor)
        mockkObject(NetworkSensor)

        sensorManager = SensorManager(context)
    }


    @Test
    fun shouldReturnListOfSensorsAllFalse(){
        sensorManager.init(CALL_PERMISSION, LOW_BATTERY, BLUETOOTH, GPS_LOCATION, DATA_AVAILABILITY, POWER_SAVING_MODE)

        val expectedList = ArrayList<Sensor>()
        expectedList.add(Sensor(CALL_PERMISSION, false))
        expectedList.add(Sensor(LOW_BATTERY, false))
        expectedList.add(Sensor(BLUETOOTH, false))
        expectedList.add(Sensor(GPS_LOCATION, false))
        expectedList.add(Sensor(DATA_AVAILABILITY, false))
        expectedList.add(Sensor(POWER_SAVING_MODE, false))


        every { PermissionsStatus.callPermissionStatus(context) }.returns(false)
        every { BatterySensor.isBatteryLow(context) }.returns(false)
        every { BluetoothSensor.isBluetoothOn() }.returns(false)
        every { NetworkSensor.isDataAvailable(context) }.returns(false)
        every { LocationSensor.isServiceLocationOn(context) }.returns(false)
        every { BatterySensor.isPowerSaveModeOn(context) }.returns(false)

        val list = sensorManager.getSensorsStatus()

        assertEquals(expectedList, list)
    }

    @Test
    fun shouldReturnListOfSensorsAllTrue() {
        sensorManager.init(CALL_PERMISSION, LOW_BATTERY, BLUETOOTH, GPS_LOCATION, DATA_AVAILABILITY, POWER_SAVING_MODE)

        val expectedList = ArrayList<Sensor>()
        expectedList.add(Sensor(CALL_PERMISSION, true))
        expectedList.add(Sensor(LOW_BATTERY, true))
        expectedList.add(Sensor(BLUETOOTH, true))
        expectedList.add(Sensor(GPS_LOCATION, true))
        expectedList.add(Sensor(DATA_AVAILABILITY, true))
        expectedList.add(Sensor(POWER_SAVING_MODE, true))


        every { PermissionsStatus.callPermissionStatus(context) }.returns(true)
        every { BatterySensor.isBatteryLow(context) }.returns(true)
        every { BluetoothSensor.isBluetoothOn() }.returns(true)
        every { NetworkSensor.isDataAvailable(context) }.returns(true)
        every { LocationSensor.isServiceLocationOn(context) }.returns(true)
        every { BatterySensor.isPowerSaveModeOn(context) }.returns(true)

        val list = sensorManager.getSensorsStatus()

        assertEquals(expectedList, list)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowUnsupportedOperationWhenProvidingMoreThanMaxSensorsList(){
        sensorManager.init(CALL_PERMISSION, LOW_BATTERY, BLUETOOTH, GPS_LOCATION, POWER_SAVING_MODE, DATA_AVAILABILITY, POWER_SAVING_MODE)
    }


    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowUnsupportedOperationWhenProvidingEmptyList(){
        sensorManager.init()
    }

    @Test
    fun shouldReturnFalseWhenLocationIsDisabled(){
        sensorManager.init(GPS_LOCATION)

        every { LocationSensor.isServiceLocationOn(context) } returns(false)
        every { LocationSensor.gpsLocationAlert(context) } returns (Unit)

        val state = sensorManager.checkSensors()
        assertEquals(false, state)
    }

    @Test
    fun shouldReturnTrueWhenLocationIsEnabled(){
        sensorManager.init(GPS_LOCATION)

        every { LocationSensor.isServiceLocationOn(context) } returns(true)
        every { LocationSensor.gpsLocationAlert(context) } returns (Unit)

        val state = sensorManager.checkSensors()
        assertEquals(true, state)
    }

    @Test
    fun shouldReturnFalseWhenBluetoothIsDisabled(){
        sensorManager.init(BLUETOOTH)

        every { BluetoothSensor.isBluetoothOn() } returns(false)
        every { BluetoothSensor.bluetoothAlert(context) } returns (Unit)

        val state = sensorManager.checkSensors()
        assertEquals(false, state)
    }

    @Test
    fun shouldReturnTrueWhenBluetoothIsEnabled(){
        sensorManager.init(BLUETOOTH)

        every { BluetoothSensor.isBluetoothOn() } returns(true)

        val state = sensorManager.checkSensors()
        assertEquals(true, state)
    }

    @Test
    fun shouldReturnFalseWhenBatteryIsLow(){
        sensorManager.init(LOW_BATTERY)

        every { BatterySensor.isBatteryLow(context) } returns(false)
        every { BatterySensor.lowBatteryAlert(context) } returns(Unit)


        val state = sensorManager.checkSensors()
        assertEquals(false, state)
    }

    @Test
    fun shouldReturnTrueWhenBatteryIsNotLow(){
        sensorManager.init(LOW_BATTERY)

        every { BatterySensor.isBatteryLow(context) } returns(true)

        val state = sensorManager.checkSensors()
        assertEquals(true, state)
    }

    @Test
    fun shouldReturnFalseWhenPowerSavingModeIsOn(){
        sensorManager.init(POWER_SAVING_MODE)

        every { BatterySensor.isPowerSaveModeOn(context) } returns(false)

        val state = sensorManager.checkSensors()
        assertEquals(false, state)
    }

    @Test
    fun shouldReturnTrueWhenPowerSavingModeIsOff(){
        sensorManager.init(POWER_SAVING_MODE)

        every { BatterySensor.isPowerSaveModeOn(context) } returns(true)

        val state = sensorManager.checkSensors()
        assertEquals(true, state)
    }

    @Test
    fun shouldReturnFalseWhenDataIsNotAvailable(){
        sensorManager.init(DATA_AVAILABILITY)

        every { NetworkSensor.isDataAvailable(context) } returns(false)

        val state = sensorManager.checkSensors()
        assertEquals(false, state)
    }

    @Test
    fun shouldReturnTrueWhenNoDataIsAvailable(){
        sensorManager.init(DATA_AVAILABILITY)

        every { NetworkSensor.isDataAvailable(context) } returns(true)

        val state = sensorManager.checkSensors()
        assertEquals(true, state)
    }

    @Test
    fun shouldReturnFalseWhenPermissionIsNotGranted(){
        sensorManager.init(CALL_PERMISSION)

        every { PermissionsStatus.callPermissionStatus(context) } returns(false)

        val state = sensorManager.checkSensors()
        assertEquals(false, state)
    }

    @Test
    fun shouldReturnTrueWhenPermissionIsGranted(){
        sensorManager.init(CALL_PERMISSION)

        every { PermissionsStatus.callPermissionStatus(context) } returns(true)

        val state = sensorManager.checkSensors()
        assertEquals(true, state)
    }

}