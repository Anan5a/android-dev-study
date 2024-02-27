package com.sayempro.fragmentsdialog

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var showDialog: Button
    lateinit var textViewName: TextView
    lateinit var textViewAge: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showDialog = findViewById(R.id.button2)
        textViewName = findViewById(R.id.textViewName)
        textViewAge = findViewById(R.id.textViewAge)


        showDialog.setOnClickListener {
            val fragmentManager = supportFragmentManager
            val myDialogFragment = MyDialogFragment()
            myDialogFragment.isCancelable = false
            myDialogFragment.show(fragmentManager, "MyDialogFragment")
        }
    }


    fun getUserData(userName: String, userAge: Int) {
        textViewAge.text = "Age: $userAge"
        textViewName.text = "Name: $userName"
    }
}