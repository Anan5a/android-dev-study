package com.sayempro.quizgame

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sayempro.quizgame.databinding.ActivityQuizBinding
import kotlin.random.Random

class QuizActivity : AppCompatActivity() {
    lateinit var quizBinding: ActivityQuizBinding
    private val database = Firebase.database("https://quiz-game-7b97d-default-rtdb.asia-southeast1.firebasedatabase.app")
    private val databaseReference = database.reference.child("questions")
    val databaseReferenceScore = database.reference.child("scores")
    private val auth = Firebase.auth
    val user = auth.currentUser

    var question = ""
    lateinit var answers: ArrayList<String>
    var correctIndex: Int = -1
    var questionCount = 0
    var questionNumber = 1
    var userAnswer = -1
    var userCorrect = 0
    var userWrong = 0
    var questions = HashSet<Int>()
    val maxQuestions = 3

    lateinit var timer: CountDownTimer
    private val totalTime = 25000L // in millisec

    var timerContinue = false
    var leftTime = 25000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        quizBinding = ActivityQuizBinding.inflate(layoutInflater)
        val view = quizBinding.root

        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        generateRandomIds()
        gameLogic()
        quizBinding.buttonNext.setOnClickListener {
            resetTimer()
            gameLogic()
        }
        quizBinding.buttonFinish.setOnClickListener {
            sendScore()
        }
        quizBinding.textViewA.setOnClickListener {
            userAnswer = 0
            checkAnswer()
        }
        quizBinding.textViewB.setOnClickListener {
            userAnswer = 1
            checkAnswer()
        }
        quizBinding.textViewC.setOnClickListener {
            userAnswer = 2
            checkAnswer()
        }
        quizBinding.textViewD.setOnClickListener {
            userAnswer = 3
            checkAnswer()
        }
    }

    private fun gameLogic() {
        quizBinding.buttonNext.isClickable = false
        databaseReference.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    questionCount = snapshot.childrenCount.toInt()

                    if (questionNumber < questions.size) {
                        question = snapshot.child(questions.elementAt(questionNumber).toString()).child("question").value.toString()
                        answers = snapshot.child(questions.elementAt(questionNumber).toString()).child("options").value as ArrayList<String>
                        correctIndex =
                            snapshot.child(
                                questions.elementAt(questionNumber).toString(),
                            ).child("correctIndex").value.toString().toInt()

                        quizBinding.textViewQuestion.text = question
                        quizBinding.textViewA.text = answers[0]
                        quizBinding.textViewB.text = answers[1]
                        quizBinding.textViewC.text = answers[2]
                        quizBinding.textViewD.text = answers[3]
                        quizBinding.progressBarQuiz.visibility = View.INVISIBLE
                        quizBinding.linearLayoutInfo.visibility = View.VISIBLE
                        quizBinding.linearLayoutQuestion.visibility = View.VISIBLE
                        quizBinding.linearLayoutButtons.visibility = View.VISIBLE
                        enableClickableOptions()
                        quizBinding.textViewQuestion.setBackgroundColor(Color.WHITE)

                        startTimer()
                    } else {
                        showDialogMessage()
                    }
                    questionNumber++
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, error.message, Toast.LENGTH_LONG).show()
                }
            },
        )
    }

    private fun showDialogMessage() {
        val dialogMessage = AlertDialog.Builder(this@QuizActivity)
        dialogMessage.setTitle(
            "Quiz Game",
        ).setMessage("Congratulations!!!\nYou have answered all questions. Do you want to see the result?")
            .setCancelable(false)
            .setPositiveButton("See Result") { dialog, which ->
                sendScore()
            }
            .setNegativeButton("Play Again") { dialog, which ->
                val intent = Intent(this@QuizActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        dialogMessage.create().show()
    }

    private fun checkAnswer() {
        quizBinding.buttonNext.isClickable = true
        disableClickableOptions()
        pauseTimer()
        when {
            userAnswer == correctIndex && userAnswer == 0 -> {
                quizBinding.textViewA.setBackgroundColor(Color.GREEN)
                userCorrect++
            }
            userAnswer == correctIndex && userAnswer == 1 -> {
                quizBinding.textViewB.setBackgroundColor(Color.GREEN)
                userCorrect++
            }
            userAnswer == correctIndex && userAnswer == 2 -> {
                quizBinding.textViewC.setBackgroundColor(Color.GREEN)
                userCorrect++
            }
            userAnswer == correctIndex && userAnswer == 3 -> {
                quizBinding.textViewD.setBackgroundColor(Color.GREEN)
                userCorrect++
            }
            else -> {
                when (userAnswer) {
                    0 -> quizBinding.textViewA.setBackgroundColor(Color.RED)
                    1 -> quizBinding.textViewB.setBackgroundColor(Color.RED)
                    2 -> quizBinding.textViewC.setBackgroundColor(Color.RED)
                    3 -> quizBinding.textViewD.setBackgroundColor(Color.RED)
                }
                when (correctIndex) {
                    0 -> quizBinding.textViewA.setBackgroundColor(Color.GREEN)
                    1 -> quizBinding.textViewB.setBackgroundColor(Color.GREEN)
                    2 -> quizBinding.textViewC.setBackgroundColor(Color.GREEN)
                    3 -> quizBinding.textViewD.setBackgroundColor(Color.GREEN)
                }
                userWrong++
            }
        }

        quizBinding.textViewCorrect.text = userCorrect.toString()
        quizBinding.textViewWrong.text = userWrong.toString()
    }

    fun disableClickableOptions() {
        quizBinding.textViewA.isClickable = false
        quizBinding.textViewB.isClickable = false
        quizBinding.textViewC.isClickable = false
        quizBinding.textViewD.isClickable = false
    }

    fun enableClickableOptions() {
        quizBinding.textViewA.isClickable = true
        quizBinding.textViewB.isClickable = true
        quizBinding.textViewC.isClickable = true
        quizBinding.textViewD.isClickable = true
        quizBinding.textViewA.setBackgroundColor(Color.WHITE)
        quizBinding.textViewB.setBackgroundColor(Color.WHITE)
        quizBinding.textViewC.setBackgroundColor(Color.WHITE)
        quizBinding.textViewD.setBackgroundColor(Color.WHITE)
    }

    fun startTimer() {
        timer =
            object : CountDownTimer(leftTime, 1000L) {
                override fun onTick(millisUntilFinished: Long) {
                    leftTime = millisUntilFinished
                    updateCountDownText()
                }

                override fun onFinish() {
                    disableClickableOptions()
                    resetTimer()
                    updateCountDownText()
                    quizBinding.textViewQuestion.text = "Timeout! Continue with next question."
                    quizBinding.textViewQuestion.setBackgroundColor(Color.RED)
                    timerContinue = false
                }
            }.start()
        timerContinue = true
    }

    private fun resetTimer() {
        pauseTimer()
        leftTime = totalTime
        updateCountDownText()
    }

    private fun updateCountDownText() {
        val remainingTime = (leftTime / 1000L).toInt()
        quizBinding.textViewTime.text = remainingTime.toString()
    }

    fun pauseTimer() {
        timer.cancel()
        timerContinue = false
    }

    fun sendScore() {
        user?.let {
            val userUid = it.uid
            databaseReferenceScore.child(userUid).child("correct").setValue(userCorrect)
            databaseReferenceScore.child(userUid).child("wrong").setValue(userWrong).addOnSuccessListener {
                Toast.makeText(applicationContext, "Score saved", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@QuizActivity, ResultActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    fun generateRandomIds() {
        do {
            val num = Random.nextInt(1, 7)
            questions.add(num)
        } while (questions.size < maxQuestions)
    }
}
