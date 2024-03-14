package com.sayempro.firebase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.sayempro.firebase.databinding.ActivityUpdateUserBinding

class UpdateUserActivity : AppCompatActivity() {
    lateinit var updateUserBinding: ActivityUpdateUserBinding
    val database = Firebase.database
    val reference = database.reference.child("MyUsers")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateUserBinding = ActivityUpdateUserBinding.inflate(layoutInflater)
        val view = updateUserBinding.root
        setContentView(view)
        supportActionBar?.title = "Update user"
        populateEditFields()

        updateUserBinding.buttonUpdate.setOnClickListener {
            updateData()
        }

    }

    fun populateEditFields() {
        val email: String = intent.getStringExtra("email") ?: ""
        val name: String = intent.getStringExtra("name") ?: ""
        val age: Int = intent.getIntExtra("age", -1)

        updateUserBinding.editTextAgeUpdate.setText(age.toString())
        updateUserBinding.editTextNameUpdate.setText(name)
        updateUserBinding.editTextEmailUpdate.setText(email)
    }

    fun updateData(){
        val email: String = updateUserBinding.editTextEmailUpdate.text.toString()
        val name: String = updateUserBinding.editTextNameUpdate.text.toString()
        val age: Int = updateUserBinding.editTextAgeUpdate.text.toString().toInt()
        val id: String = intent.getStringExtra("id") ?: ""

        val user = mapOf<String, Any>("userId" to id,"userName" to name, "userAge" to age,"userEmail" to email)
        reference.child(id).updateChildren(user).addOnCompleteListener {task->
            if (task.isSuccessful){
                Toast.makeText(applicationContext, "User updated successfully", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "User update failed", Toast.LENGTH_SHORT).show()
            }
            finish()
        }

    }
}