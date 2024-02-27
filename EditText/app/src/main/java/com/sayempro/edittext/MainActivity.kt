package com.sayempro.edittext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    lateinit var textInputName: EditText
    lateinit var submitButton: Button
    lateinit var textView: TextView
    lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textInputName = findViewById(R.id.editTextTextPersonName)
        textView = findViewById(R.id.textView)
        submitButton = findViewById(R.id.submitButton)
        image = findViewById(R.id.imageView)

        submitButton.setOnClickListener {
            image.setImageResource(R.drawable.image2)
            textView.text = textInputName.text.toString()
        }

    }
}