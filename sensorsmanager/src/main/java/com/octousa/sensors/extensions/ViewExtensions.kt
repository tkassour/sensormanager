package com.octousa.sensors.extensions

import android.widget.ExpandableListView
import com.octousa.sensors.model.Sensor
import com.octousa.sensors.ui.views.ExpandableListAdapter

/**
 * Created by taoufik.kassour on 11/15/18/ 08
 */

/**
 *
 * Added new functionality of ExpandableListView
 * to set custom data easily
 *
 */
fun ExpandableListView.setData(data : ArrayList<Sensor>) {
    val listAdapter = ExpandableListAdapter(context, data)
    setAdapter(listAdapter)
}




