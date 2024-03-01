package com.sayempro.devicefeaturesms

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    lateinit var message: EditText
    lateinit var number: EditText
    lateinit var send: Button

    var messageText: String = ""
    var phoneNumber: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        message = findViewById(R.id.editTextTextMessage)
        number = findViewById(R.id.editTextPhone)
        send = findViewById(R.id.buttonSend)

        send.setOnClickListener {
            messageText = message.text.toString()
            phoneNumber = number.text.toString()
            sendSMS(userMessage = messageText, userNumber = phoneNumber)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            val smsManager: SmsManager = if (Build.VERSION.SDK_INT >= 23) {
                this.getSystemService(SmsManager::class.java)
            } else {
                SmsManager.getDefault()
            }
            smsManager.sendTextMessage(phoneNumber, null, messageText, null, null)
            Toast.makeText(applicationContext, "Message Sent", Toast.LENGTH_LONG).show()
        }
    }

    private fun sendSMS(userMessage: String, userNumber: String) {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 1)
        } else {
            val smsManager: SmsManager = if (Build.VERSION.SDK_INT >= 23) {
                this.getSystemService(SmsManager::class.java)
            } else {
                SmsManager.getDefault()
            }
            smsManager.sendTextMessage(userNumber, null, userMessage, null, null)
            Toast.makeText(applicationContext, "Message Sent", Toast.LENGTH_LONG).show()
        }
    }

}