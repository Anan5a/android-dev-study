package com.sayempro.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sayempro.firebase.databinding.ActivityLoginBinding
import com.sayempro.firebase.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    val auth = Firebase.auth

    lateinit var signupBinding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signupBinding=ActivitySignupBinding.inflate(layoutInflater)
        val view=signupBinding.root
        setContentView(view)

        signupBinding.buttonSignup.setOnClickListener {
            val userEmail = signupBinding.editTextEmailSignup.text.toString()
            val userPassword = signupBinding.editTextPasswordSignup.text.toString()
            signupWithFirebase(userEmail, userPassword)
        }
    }

    fun signupWithFirebase(userEmail:String, userPassword:String){
        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener { task->
            if (task.isSuccessful){
                Toast.makeText(applicationContext, "Account created successfully", Toast.LENGTH_LONG).show()
                finish()
            }else{
                Toast.makeText(applicationContext, task.exception.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }
}