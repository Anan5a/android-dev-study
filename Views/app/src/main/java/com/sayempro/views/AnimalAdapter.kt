package com.sayempro.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class AnimalAdapter(
    var nameList : List<String>,
    var imageList : List<Int>,
    var context:Context
):BaseAdapter() {

    override fun getCount(): Int {
        return nameList.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view:View = LayoutInflater.from(parent?.context).inflate(R.layout.grid_item, parent, false)
        var name:TextView = view.findViewById(R.id.textView)
        var animalImage:ImageView = view.findViewById(R.id.imageView)

        name.text = nameList[position].toString()
        animalImage.setImageResource(imageList[position])

        return view
    }
}