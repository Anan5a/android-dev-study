package com.sayempro.multilang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var showToast:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showToast=findViewById(R.id.buttonToast)
        showToast.setOnClickListener {
            Toast.makeText(applicationContext, R.string.toast_message, Toast.LENGTH_LONG).show()
        }
    }
}