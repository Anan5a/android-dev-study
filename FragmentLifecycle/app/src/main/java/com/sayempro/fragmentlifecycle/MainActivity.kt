package com.sayempro.fragmentlifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("appState", "MainActivity onCreate()")

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