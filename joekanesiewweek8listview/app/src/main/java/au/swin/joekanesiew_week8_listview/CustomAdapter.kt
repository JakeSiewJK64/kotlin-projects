package au.swin.joekanesiew_week8_listview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

private class ViewHolder(val name: TextView, val desc: TextView)

class CustomAdapter : BaseAdapter() {

    private val arrayName = arrayListOf("Python", "Javascript", "php", "ASP.NET", "Kotlin", "C++")

    override fun getCount(): Int {
        return arrayName.size
    }

    override fun getItem(p0: Int): Any {
        return 0
    }

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
        val rowLayout: View

        if (convertView == null) {
            val layoutInflater = LayoutInflater.from(viewGroup?.context)
            rowLayout = layoutInflater.inflate(R.layout.viewmodel, viewGroup, false)
            Log.i("HAPPENINGS", "Position: ${position}")
            val itemName = rowLayout.findViewById<TextView>(R.id.viewholderTextView1)
            val itemDesc = rowLayout.findViewById<TextView>(R.id.viewholderTextView2)
            val viewHolder = ViewHolder(itemName, itemDesc)
            rowLayout.tag = viewHolder
        } else {
            rowLayout = convertView
        }

        val viewHolder = rowLayout.tag as ViewHolder
        viewHolder.name.text = arrayName[position]
        viewHolder.desc.text = "item number $position"
        return rowLayout

//        val rowLayout = layoutInflater.inflate(R.layout.viewholder, viewGroup, false)
//        val itemName = rowLayout.findViewById<TextView>(R.id.viewholderTextView1)
//        val itemDesc = rowLayout.findViewById<TextView>(R.id.viewholderTextView2)
//        itemName.text = arrayName[position]
//        itemDesc.text = "Item number $position"
//        return rowLayout
//        val textView: TextView = TextView(viewGroup?.context)
//        textView.text = "Items ${position}"
//        return textView
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }
}