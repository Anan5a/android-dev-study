package com.sayempro.textviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var myText:TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myText=findViewById(R.id.textExample)
        myText.setTextColor(Color.BLACK)
        myText.setOnClickListener {
            myText.text = "You clicked me!"
            myText.setBackgroundColor(Color.WHITE)
        }
    }
}