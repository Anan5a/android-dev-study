package com.sayempro.togglebuttons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.ToggleButton
import androidx.core.view.get

class MainActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener {

//    lateinit var imageView:ImageView
//    lateinit var textView:TextView
//    lateinit var toggleButton: ToggleButton
    lateinit var spinner: Spinner
    lateinit var result:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spinner_main)

        spinner=findViewById(R.id.spinner)
        result=findViewById(R.id.result)
        spinner.onItemSelectedListener = this

        var arrayAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.countries,
            android.R.layout.simple_spinner_item
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

//        imageView=findViewById(R.id.imageView)
//        textView=findViewById(R.id.textView)
//        toggleButton=findViewById(R.id.toggleButton)
//
//        toggleButton.setOnCheckedChangeListener { buttonView, isChecked ->
//            when(isChecked){
//                true-> {
//                    imageView.visibility = View.INVISIBLE
//                    textView.text = "The image is invisible."
//                }
//                false->{
//                    imageView.visibility = View.VISIBLE
//                    textView.text = "The image is visible."
//
//                }
//            }
//        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        result.text = parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}