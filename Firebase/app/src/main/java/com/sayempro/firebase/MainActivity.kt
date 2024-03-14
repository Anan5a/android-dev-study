package com.sayempro.firebase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class MainActivity : AppCompatActivity() {
    lateinit var etName: EditText
    lateinit var tvName: TextView
    lateinit var sendButton: Button
    val database = Firebase.database
    val reference = database.reference.child("Users")
    val reference2 = database.reference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.editTextName)
        tvName = findViewById(R.id.textViewName)
        sendButton = findViewById(R.id.buttonSend)

        reference2.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.child("Users").child("userName").value as String
                tvName.text = data
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


        sendButton.setOnClickListener {
            val userName = etName.text.toString()
            reference.child("userName").setValue(userName)
        }

    }
}