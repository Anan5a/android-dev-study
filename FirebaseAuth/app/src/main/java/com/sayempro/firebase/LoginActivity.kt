package com.sayempro.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sayempro.firebase.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var loginBinding: ActivityLoginBinding
    val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = loginBinding.root
        setContentView(view)


        loginBinding.buttonSignup.setOnClickListener {
            val intent = Intent(applicationContext, SignupActivity::class.java)
            startActivity(intent)
        }
        loginBinding.buttonSignin.setOnClickListener {
            val userEmail = loginBinding.editTextEmailSignin.text.toString()
            val userPassword = loginBinding.editTextPasswordSignin.text.toString()
            if (userEmail.isBlank() || userPassword.isBlank()){
                Toast.makeText(applicationContext, "Please enter correct credentials", Toast.LENGTH_LONG).show()

            }else{
                signinWithFirebase(userEmail, userPassword)

            }
        }
        loginBinding.buttonForgot.setOnClickListener {
            val intent = Intent(this, ForgetActivity::class.java)
            startActivity(intent)
        }
        loginBinding.buttonSigninPhone.setOnClickListener {
            val intent = Intent(this, SigninPhoneActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun signinWithFirebase(userEmail: String, userPassword: String) {
        auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener { task->
            if (task.isSuccessful){
                Toast.makeText(applicationContext, "Login successfully", Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(applicationContext, task.exception.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        val user = auth.currentUser
        if (user!=null){
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}