package com.sayempro.firebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.auth
import com.sayempro.firebase.databinding.ActivitySigninPhoneBinding
import java.util.concurrent.TimeUnit

class SigninPhoneActivity : AppCompatActivity() {
    lateinit var signinPhoneBinding: ActivitySigninPhoneBinding
    val auth = Firebase.auth
    lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    var verificationCode = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signinPhoneBinding = ActivitySigninPhoneBinding.inflate(layoutInflater)
        val view = signinPhoneBinding.root

        setContentView(view)

        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                signinWithCredentials(credential)
            }

            override fun onVerificationFailed(exception: FirebaseException) {
                Log.e("VERIFY_EXCEPTION", exception.toString())
            }

            override fun onCodeSent(fromFirebase: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(fromFirebase, p1)
                verificationCode = fromFirebase
            }

        }

        signinPhoneBinding.buttonSendCodeSMS.setOnClickListener {
            val phoneNumber = signinPhoneBinding.editTextPhone.text.toString()

            val options = PhoneAuthOptions.newBuilder(auth).setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS).setActivity(this@SigninPhoneActivity)
                .setCallbacks(mCallbacks).build()
            PhoneAuthProvider.verifyPhoneNumber(options)

            signinPhoneBinding.buttonVerifyPhone.isEnabled = true
            signinPhoneBinding.editTextNumber.isEnabled = true

        }
        signinPhoneBinding.buttonVerifyPhone.setOnClickListener {
            val code = signinPhoneBinding.editTextNumber.text.toString()
            val credential = PhoneAuthProvider.getCredential(verificationCode, code)
            signinWithCredentials(credential)
        }
    }

    fun signinWithCredentials(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    applicationContext, "Signin with phone successful!", Toast.LENGTH_LONG
                ).show()
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(
                    applicationContext, task.exception.toString(), Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}