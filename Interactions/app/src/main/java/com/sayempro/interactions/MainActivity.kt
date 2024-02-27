package com.sayempro.interactions

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var showToast: Button
    lateinit var showSnackbar: Button
    lateinit var showAlert: Button
    lateinit var constraintLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showToast = findViewById(R.id.showToast)
        showSnackbar = findViewById(R.id.showSnackbar)
        showAlert = findViewById(R.id.showAlert)
        constraintLayout = findViewById(R.id.constraintLayout)

        showToast.setOnClickListener {
            Toast.makeText(applicationContext, "Toast to the wine!", Toast.LENGTH_LONG).show()
        }
        showSnackbar.setOnClickListener {
            Snackbar.make(
                constraintLayout, "Lets eat snack now", Snackbar.LENGTH_INDEFINITE
            ).setAction("Close", View.OnClickListener {
                Toast.makeText(applicationContext, "Snack finished!", Toast.LENGTH_SHORT).show()

            }).show()
        }
        showAlert.setOnClickListener {
            showAlertDialog()
        }
    }


    fun showAlertDialog() {
        val alertDialog = AlertDialog.Builder(
            this@MainActivity
        )
        alertDialog.setTitle("Change").setMessage("Do you want to change the text of the button?")
            .setIcon(R.drawable.ic_round_warning_24).setCancelable(false).setNegativeButton(
                "No",
                DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                showAlert.text = "Alert Dialog"
                dialog.cancel()
            })
            .setNeutralButton("Skip", DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(applicationContext, "Skipped action?", Toast.LENGTH_LONG).show()

            })
            .show()
    }
}