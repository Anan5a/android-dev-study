package com.sayempro.quizgame

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sayempro.quizgame.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {
    lateinit var forgotPasswordBinding: ActivityForgotPasswordBinding
    val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        forgotPasswordBinding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        val view = forgotPasswordBinding.root

        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        forgotPasswordBinding.buttonForgot.setOnClickListener {
            val userEmail = forgotPasswordBinding.editTextForgotEmail.text.toString()
            forgotPasswordBinding.progressBarForgot.visibility = View.VISIBLE
            forgotPasswordBinding.buttonForgot.isClickable = false
            auth.sendPasswordResetEmail(userEmail).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "A password reset email was sent to your email.", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(applicationContext, task.exception?.localizedMessage, Toast.LENGTH_LONG).show()
                }
                forgotPasswordBinding.progressBarForgot.visibility = View.INVISIBLE
                forgotPasswordBinding.buttonForgot.isClickable = true
            }
        }
    }
}
