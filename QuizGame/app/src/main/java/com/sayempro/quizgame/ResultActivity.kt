package com.sayempro.quizgame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sayempro.quizgame.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var resultBinding: ActivityResultBinding
    val database = Firebase.database("https://quiz-game-7b97d-default-rtdb.asia-southeast1.firebasedatabase.app")
    val databaseReference = database.reference.child("scores")
    val auth = Firebase.auth
    val user = auth.currentUser

    var userCorrect = ""
    var userWrong = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultBinding = ActivityResultBinding.inflate(layoutInflater)
        val view = resultBinding.root

        setContentView(view)

        databaseReference.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    user?.let {
                        val uid = it.uid
                        userCorrect = snapshot.child(uid).child("correct").value.toString()
                        userWrong = snapshot.child(uid).child("wrong").value.toString()

                        resultBinding.textViewResultCorrect.text = userCorrect
                        resultBinding.textViewResultWrong.text = userWrong
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            },
        )

        resultBinding.buttonResultPlayAgain.setOnClickListener {
            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        resultBinding.buttonResultExit.setOnClickListener {
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
