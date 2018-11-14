package com.octousa.sensors.ui.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ExpandableListView
import com.octousa.sensors.model.Sensor

/**
 * Created by taoufik.kassour on 10/31/18/ 18
 */
class SensorsStatusView : ExpandableListView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    fun setData(data : ArrayList<Sensor>){
        val listAdapter = ExpandableListAdapter(context, data)
        setAdapter(listAdapter)
    }



}