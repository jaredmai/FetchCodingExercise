package com.fetchrewards.hiringexercise

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView

class CustomAdapter(context: Context,arrayListDetails:ArrayList<DataModel>) : BaseAdapter(){

    private val layoutInflater: LayoutInflater
    private val arrayListDetails:ArrayList<DataModel>

    init {
        this.layoutInflater = LayoutInflater.from(context)
        this.arrayListDetails=arrayListDetails
    }

    override fun getCount(): Int {
        return arrayListDetails.size
    }

    override fun getItem(position: Int): Any {
        return arrayListDetails[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        val listRowHolder: ListRowHolder
        if (convertView == null) {
            view = this.layoutInflater.inflate(R.layout.adapter_layout, parent, false)
            listRowHolder = ListRowHolder(view)
            view.tag = listRowHolder
        } else {
            view = convertView
            listRowHolder = view.tag as ListRowHolder
        }

        listRowHolder.nameTextView.text = arrayListDetails[position].name
        listRowHolder.listIdTextView.text = arrayListDetails[position].listId.toString()
        listRowHolder.idTextView.text = arrayListDetails[position].id.toString()
        return view
    }
}

private class ListRowHolder(row: View?) {
    val nameTextView: TextView
    val listIdTextView: TextView
    val idTextView: TextView
    val linearLayout: LinearLayout

    init {
        this.idTextView = row?.findViewById(R.id.idTextView) as TextView
        this.nameTextView = row.findViewById(R.id.nameTextView) as TextView
        this.listIdTextView = row.findViewById(R.id.listIdTextView) as TextView
        this.linearLayout = row.findViewById(R.id.linearLayout) as LinearLayout
    }
}