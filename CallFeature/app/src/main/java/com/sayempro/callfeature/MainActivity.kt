package com.sayempro.callfeature

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sayempro.callfeature.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    var userNumber: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = mainBinding.root
        setContentView(view)

        mainBinding.buttonCall.setOnClickListener {
            userNumber = mainBinding.editTextPhone.text.toString()
            makeCall(userNumber)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$userNumber")
            callIntent.putExtra(Intent.EXTRA_PHONE_NUMBER, userNumber)
            startActivity(callIntent)
        }
    }

    private fun makeCall(userNumber: String) {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //ask for permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 100)
        } else {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$userNumber")
            callIntent.putExtra(Intent.EXTRA_PHONE_NUMBER, userNumber)
            startActivity(callIntent)
        }
    }
}