package com.sayempro.intents

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var age: EditText
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("appState", "onCreate()")

        name = findViewById(R.id.editTextTextName)
        age = findViewById(R.id.editTextNumberAge)
        button = findViewById(R.id.buttonSend)

        button.setOnClickListener {
            val userName: String = name.text.toString()
            val userAge: Int = age.text.toString().toInt()

            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            intent.putExtra("name", userName)
            intent.putExtra("age", userAge)
            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("appState", "onRestart()")

    }

    override fun onPause() {
        super.onPause()
        Log.d("appState", "onPause()")

    }

    override fun onResume() {
        super.onResume()
        Log.d("appState", "onResume()")

    }

    override fun onStart() {
        super.onStart()
        Log.d("appState", "onStart()")

    }

    override fun onStop() {
        super.onStop()
        Log.d("appState", "onStop()")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("appState", "onDestroy()")

    }
}