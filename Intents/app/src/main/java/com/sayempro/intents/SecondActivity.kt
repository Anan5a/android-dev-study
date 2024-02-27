package com.sayempro.intents

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        result = findViewById(R.id.textView)

        val userName: String = intent.getStringExtra("name") ?: ""
        val userAge: Int = intent.getIntExtra("age", 0)
        result.text = "Your name is $userName and you are $userAge years old!"

    }
}