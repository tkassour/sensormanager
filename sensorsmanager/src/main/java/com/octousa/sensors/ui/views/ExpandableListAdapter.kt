package com.octousa.sensors.ui.views

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import com.octousa.sensors.R
import com.octousa.sensors.model.Sensor
import com.octousa.sensors.utils.SensorsConstants.getSensorDesc
import java.util.*

/**
 * Created by taoufik.kassour on 10/31/18/ 09
 */


class ExpandableListAdapter(private val context: Context, val data: ArrayList<Sensor>) : BaseExpandableListAdapter() {

    //Group
    override fun getGroup(groupPosition: Int): Sensor {
        return data[groupPosition]
    }

    override fun getGroupCount(): Int {
        return data.size
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        val header = getGroup(groupPosition)
        if (convertView == null) {
            val infalInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = infalInflater.inflate(R.layout.list_group, null)
        }

        val icon = convertView!!.findViewById<ImageView>(R.id.checkIcon)
        val lblListHeader = convertView.findViewById<View>(R.id.lblListHeader) as TextView

        if(header.status)
            icon.setImageResource(R.drawable.ic_check)
        else
            icon.setImageResource(R.drawable.ic_error_outline)

        lblListHeader.setTypeface(null, Typeface.BOLD)
        lblListHeader.text = header.name

        return convertView
    }

    //Children
    override fun getChild(groupPosition: Int, childPosititon: Int): Sensor {
        return data[groupPosition]
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        val child = getChild(groupPosition, childPosition)

        if (convertView == null) {
            val infalInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = infalInflater.inflate(R.layout.list_item, null)
        }

        val icon = convertView!!.findViewById<ImageView>(R.id.fixItIcon)
        val txtListChild = convertView.findViewById<View>(R.id.lblListItem) as TextView

        txtListChild.text = getSensorDesc(child.name)

        if(!child.status)
            icon.setImageResource(R.drawable.ic_fix_it)

        return convertView
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return 1
    }

    // Other
    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}
