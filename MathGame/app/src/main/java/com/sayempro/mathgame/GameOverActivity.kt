package com.sayempro.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.system.exitProcess

class GameOverActivity : AppCompatActivity() {

    lateinit var gameScoreText: TextView
    lateinit var playAgain: Button
    lateinit var exit: Button

    private var gameScore = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        gameScoreText = findViewById(R.id.textViewGameOverScore)
        playAgain = findViewById(R.id.buttonPlayAgain)
        exit = findViewById(R.id.buttonExit)

        gameScore = intent.getIntExtra("gameScore", 0)

        gameScoreText.text = "Your score: $gameScore"

        playAgain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        exit.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)

        }

    }
}