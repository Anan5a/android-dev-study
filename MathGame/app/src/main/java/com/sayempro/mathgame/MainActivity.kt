package com.sayempro.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var additionButton: Button
    lateinit var subtractionButton: Button
    lateinit var multiplicationButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        additionButton = findViewById(R.id.buttonAddition)
        subtractionButton = findViewById(R.id.buttonSubtraction)
        multiplicationButton = findViewById(R.id.buttonMultiplication)

        additionButton.setOnClickListener {
            val intent = Intent(applicationContext, GameActivity::class.java)
            intent.putExtra("gameType", "addition")
            startActivity(intent)
        }
        subtractionButton.setOnClickListener {
            val intent = Intent(applicationContext, GameActivity::class.java)
            intent.putExtra("gameType", "subtraction")
            startActivity(intent)
        }
        multiplicationButton.setOnClickListener {
            val intent = Intent(applicationContext, GameActivity::class.java)
            intent.putExtra("gameType", "multiply")
            startActivity(intent)
        }

    }
}