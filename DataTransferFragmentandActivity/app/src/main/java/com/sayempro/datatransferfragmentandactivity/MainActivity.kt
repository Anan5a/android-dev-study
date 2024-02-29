package com.sayempro.datatransferfragmentandactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var tvName: TextView
    lateinit var tvEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvName = findViewById(R.id.textViewName)
        tvEmail = findViewById(R.id.textViewEmail)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = InputFragment()
        fragmentTransaction.add(R.id.frameLayout, fragment)
        fragmentTransaction.commit()

    }

    fun takeData(name: String, email: String)
    {
        tvName.text = "Hello there, $name"
        tvEmail.text = "Your email is, $email"
    }
}