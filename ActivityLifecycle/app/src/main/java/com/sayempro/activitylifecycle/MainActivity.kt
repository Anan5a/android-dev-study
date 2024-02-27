package com.sayempro.activitylifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var nextActivityButton: Button
    lateinit var plusOneButton: Button
    lateinit var textView: TextView
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("appState", "MainActivity onCreate()")

        nextActivityButton = findViewById(R.id.buttonNextActivity)
        plusOneButton = findViewById(R.id.buttonPlusOne)
        textView = findViewById(R.id.textView)

        plusOneButton.setOnClickListener {
            counter++
            textView.text = "$counter"
        }
        nextActivityButton.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)

            startActivity(intent)
        }

    }

    override fun onRestart() {
        super.onRestart()
        Log.d("appState", "MainActivity onRestart()")

    }

    override fun onPause() {
        super.onPause()
        Log.d("appState", "MainActivity onPause()")

    }

    override fun onResume() {
        super.onResume()
        Log.d("appState", "MainActivity onResume()")

    }

    override fun onStart() {
        super.onStart()
        Log.d("appState", "MainActivity onStart()")

    }

    override fun onStop() {
        super.onStop()
        Log.d("appState", "MainActivity onStop()")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("appState", "MainActivity onDestroy()")

    }
}