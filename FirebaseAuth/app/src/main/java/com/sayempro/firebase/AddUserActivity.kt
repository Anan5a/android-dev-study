package com.sayempro.firebase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.sayempro.firebase.databinding.ActivityAddUserBinding

class AddUserActivity : AppCompatActivity() {
    lateinit var addUserBinding: ActivityAddUserBinding
    val database = Firebase.database
    val reference = database.reference.child("MyUsers")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addUserBinding = ActivityAddUserBinding.inflate(layoutInflater)
        val view = addUserBinding.root
        setContentView(view)
        supportActionBar?.title = "Add new user"
        addUserBinding.buttonAdd.setOnClickListener {
            addUserToDatabase()
        }

    }


    fun addUserToDatabase(){
        val name:String = addUserBinding.editTextName.text.toString()
        val email:String = addUserBinding.editTextEmail.text.toString()
        val age:Int = addUserBinding.editTextAge.text.toString().toInt()
        val id = reference.push().key as String
        val user = User(id, name, age, email)
        reference.child(id).setValue(user).addOnCompleteListener {task->
            if (task.isSuccessful){
                Toast.makeText(applicationContext, "User added successfully", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "Failed to add new user", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }
}