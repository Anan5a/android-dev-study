package com.sayempro.services

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var startClassicService: Button
    lateinit var stopClassicService: Button
    lateinit var startJobIntentService: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startClassicService = findViewById(R.id.buttonStartClassicService)
        startJobIntentService = findViewById(R.id.buttonStartJobService)
        stopClassicService = findViewById(R.id.buttonStopClassicService)


        startClassicService.setOnClickListener {
            val intent = Intent(this@MainActivity, ClassicServiceExample::class.java)
            startService(intent)
        }
        startJobIntentService.setOnClickListener {
            val intent = Intent(this@MainActivity, JobIntentServiceExample::class.java)
            JobIntentServiceExample.myBackgroundService(this@MainActivity, intent)
//            startService(intent)
        }
        stopClassicService.setOnClickListener {
            val intent = Intent(this@MainActivity, ClassicServiceExample::class.java)
            stopService(intent)

        }

    }
}