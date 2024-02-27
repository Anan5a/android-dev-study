package com.sayempro.mathgame

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    val scoreIncrement = 10

    lateinit var scoreValue: TextView
    lateinit var lifeValue: TextView
    lateinit var timeValue: TextView
    lateinit var gameQuestion: TextView
    lateinit var gameTypeView: TextView
    lateinit var gameAnswer: EditText
    lateinit var buttonOK: Button
    lateinit var buttonNext: Button
    lateinit var timer: CountDownTimer

    private lateinit var gameType: String

    private val initialTimeInMillis: Long = 20 * 1000 //20 sec
    private var timeLeftInMillis: Long = initialTimeInMillis
    private var correctAnswer = 0
    private var userScore = 0
    private var lifeCount = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        gameType = intent.getStringExtra("gameType") ?: "addition"

        scoreValue = findViewById(R.id.gameScoreValue)
        lifeValue = findViewById(R.id.gameLifeValue)
        timeValue = findViewById(R.id.gameTimeValue)
        gameQuestion = findViewById(R.id.gameQuestion)
        gameAnswer = findViewById(R.id.gameAnswer)
        gameTypeView = findViewById(R.id.textViewGameType)

//        buttonOK = findViewById(R.id.buttonOk)
        buttonNext = findViewById(R.id.buttonNext)

        generateContinue()

        buttonNext.setOnClickListener {
            if (!checkGame()) return@setOnClickListener
            if (lifeCount <= 0) {
                //game over
                gameOver()
            } else {
                pauseTimer()
                resetTimer()
                gameQuestion.setBackgroundColor(resources.getColor(R.color.blue, null))
                generateContinue()
            }

        }
//
//        buttonOK.setOnClickListener {
//            checkGame()
//        }

    }

    fun checkGame(): Boolean {
        pauseTimer()

        val userAns = gameAnswer.text.toString()
        return if (userAns.isEmpty()) {
            toast("You did not answer!")
            false
        } else {
            val userAnsNum = userAns.toInt()
            if (userAnsNum == correctAnswer) {
                userScore += scoreIncrement
                gameQuestion.setBackgroundColor(resources.getColor(R.color.green, null))
                gameQuestion.text = "Correct answer"
                scoreValue.text = userScore.toString()
            } else {
//                gameQuestion.setBackgroundColor(Color.RED)
//                gameQuestion.text = "Wrong answer"
                lifeCount--
                lifeValue.text = lifeCount.toString()
                toast("Wrong answer")
            }
            true
        }


    }

    fun gameOver() {
        toast("Game Over!")
        val intent = Intent(this, GameOverActivity::class.java)
        intent.putExtra("gameScore", userScore)
        startActivity(intent)
        finish()
    }

    fun generateContinue() {
        startTimer()
        val n1 = Random.nextInt(0, 100)
        val n2 = Random.nextInt(0, 100)
        gameAnswer.text.clear()
        when (gameType) {
            "addition" -> {
                gameTypeView.text = "+"
                gameQuestion.text = "$n1 + $n2"
                correctAnswer = n1 + n2
            }

            "subtraction" -> {
                gameTypeView.text = "-"
                gameQuestion.text = "$n1 - $n2"
                correctAnswer = n1 - n2
            }

            "multiply" -> {
                gameTypeView.text = "*"
                gameQuestion.text = "$n1 * $n2"
                correctAnswer = n1 * n2
            }

            else -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }


    }

    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun startTimer() {
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()

                gameQuestion.setBackgroundColor(Color.RED)
                gameQuestion.text = "Time Out"
                lifeCount--
                lifeValue.text = lifeCount.toString()
            }
        }
        timer.start()
    }

    fun updateText() {
        timeValue.text = (timeLeftInMillis / 1000).toInt().toString()
    }

    fun pauseTimer() {
        timer.cancel()
    }

    fun resetTimer() {
        timeLeftInMillis = initialTimeInMillis
        updateText()
    }
}