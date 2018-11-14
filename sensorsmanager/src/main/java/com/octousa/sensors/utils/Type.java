package com.octousa.sensors.utils;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.octousa.sensors.utils.SensorsConstants.BLUETOOTH;
import static com.octousa.sensors.utils.SensorsConstants.CALL_PERMISSION;
import static com.octousa.sensors.utils.SensorsConstants.DATA_AVAILABILITY;
import static com.octousa.sensors.utils.SensorsConstants.GPS_LOCATION;
import static com.octousa.sensors.utils.SensorsConstants.LOW_BATTERY;
import static com.octousa.sensors.utils.SensorsConstants.POWER_SAVING_MODE;

/**
 * Created by taoufik.kassour on 11/7/18
 *
 * Annotation to strict SensorManager input data to only use the below @StringDef values
 *
 */
@Target(ElementType.PARAMETER)
@StringDef({GPS_LOCATION, BLUETOOTH, POWER_SAVING_MODE, LOW_BATTERY, DATA_AVAILABILITY, CALL_PERMISSION})
@Retention(RetentionPolicy.SOURCE)
public @interface Type {
}
