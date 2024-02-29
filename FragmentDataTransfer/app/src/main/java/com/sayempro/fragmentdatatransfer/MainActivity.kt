package com.sayempro.fragmentdatatransfer

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var etWeight: EditText
    lateinit var etHeight: EditText
    lateinit var calculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etHeight = findViewById(R.id.editTextNumberHeight)
        etWeight = findViewById(R.id.editTextNumberWeight)
        calculate = findViewById(R.id.buttonCalculate)

        val fragmentManager = supportFragmentManager
        calculate.setOnClickListener {
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = BMIFragment()
            val weight = etWeight.text.toString().toDouble()
            val height = etHeight.text.toString().toDouble()
            val bundle = Bundle()
            bundle.putDouble("weight", weight)
            bundle.putDouble("height", height)

            fragment.arguments = bundle
            fragmentTransaction.replace(R.id.frameLayout, fragment)
            fragmentTransaction.commit()
            etWeight.text.clear()
            etHeight.text.clear()
        }

    }
}