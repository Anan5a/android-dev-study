package com.sayempro.firebase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.sayempro.firebase.databinding.ActivityForgetBinding

class ForgetActivity : AppCompatActivity() {
    lateinit var forgetBinding: ActivityForgetBinding
    val auth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forgetBinding = ActivityForgetBinding.inflate(layoutInflater)
        val view = forgetBinding.root
        setContentView(view)

        forgetBinding.buttonForgotForm.setOnClickListener {
            val email = forgetBinding.editTextEmailForgot.text.toString()
            if (!email.isBlank()){
                auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            "We sent a password reset link to your email",
                            Toast.LENGTH_LONG
                        ).show()
                        finish()
                    }
                }
            }
        }
    }
}