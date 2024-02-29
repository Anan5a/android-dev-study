package com.sayempro.datatransfer

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    lateinit var tvName: TextView
    lateinit var tvPhone: TextView
    lateinit var tvEmail: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        tvName = findViewById(R.id.textViewName)
        tvEmail = findViewById(R.id.textViewEmail)
        tvPhone = findViewById(R.id.textViewPhone)

        val name = intent.getStringExtra("name") ?: "Did not get anything!"
        val email = intent.getStringExtra("email") ?: "Did not get anything!"
        val phone = intent.getLongExtra("phone", 0L)

        tvName.text = "Hello, $name"
        tvEmail.text = "Your email: $email"
        tvPhone.text = "Your phone is, $phone"
    }
}