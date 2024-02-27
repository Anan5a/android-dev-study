package com.sayempro.activitylifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class SecondActivity : AppCompatActivity() {
    lateinit var goBackButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        goBackButton = findViewById(R.id.buttonGotoFirstActivity)

        goBackButton.setOnClickListener {
            val intent = Intent(this@SecondActivity, MainActivity::class.java)

            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("appState", "SecondActivity onRestart()")

    }

    override fun onPause() {
        super.onPause()
        Log.d("appState", "SecondActivity onPause()")

    }

    override fun onResume() {
        super.onResume()
        Log.d("appState", "SecondActivity onResume()")

    }

    override fun onStart() {
        super.onStart()
        Log.d("appState", "SecondActivity onStart()")

    }

    override fun onStop() {
        super.onStop()
        Log.d("appState", "SecondActivity onStop()")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("appState", "SecondActivity onDestroy()")

    }
    
}