package com.sayempro.devicefeatureemail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var etEmail: EditText
    lateinit var emailTitle: EditText
    lateinit var etMessage: EditText
    lateinit var send: Button

    lateinit var title:String
    lateinit var email:String
    lateinit var message:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etEmail = findViewById(R.id.editTextTextEmailAddress)
        emailTitle = findViewById(R.id.editTextText)
        etMessage = findViewById(R.id.editTextTextMultiLine)
        send = findViewById(R.id.buttonSend)

        send.setOnClickListener {
            title=emailTitle.text.toString()
            email=etEmail.text.toString()
            message=etMessage.text.toString()
            sendEmail(email, title, message)
        }

    }

    fun sendEmail(email: String, title: String, message: String) {
        val emailAddresses = arrayOf(email)

        val emailIntent = Intent(Intent.ACTION_SEND)
//        emailIntent.data = Uri.parse("mailto:")
        emailIntent.type = "*/*"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddresses)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, title)
        emailIntent.putExtra(Intent.EXTRA_TEXT, message)

        if (emailIntent.resolveActivity(packageManager) != null){
            startActivity(Intent.createChooser(emailIntent, "Choose an app to send email"))
        }


    }
}