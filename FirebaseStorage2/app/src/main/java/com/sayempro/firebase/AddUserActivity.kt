package com.sayempro.firebase

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.google.firebase.storage.storage
import com.sayempro.firebase.databinding.ActivityAddUserBinding
import com.squareup.picasso.Picasso
import java.util.UUID

class AddUserActivity : AppCompatActivity() {
    lateinit var addUserBinding: ActivityAddUserBinding
    val database = Firebase.database
    val reference = database.reference.child("MyUsers")
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    var imageUri: Uri? = null
    val firebaseStorage = Firebase.storage
    val firebaseStorageReference = firebaseStorage.reference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addUserBinding = ActivityAddUserBinding.inflate(layoutInflater)
        val view = addUserBinding.root
        setContentView(view)
        supportActionBar?.title = "Add new user"
        registerForActivityForResult()

        addUserBinding.buttonAdd.setOnClickListener {
            uploadPhoto()
        }

        addUserBinding.imageViewProfile.setOnClickListener {
            chooseImage()
        }


    }


    fun registerForActivityForResult() {
        activityResultLauncher =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult(),
                ActivityResultCallback { result ->
                    val resultCode = result.resultCode
                    val imageData = result.data
                    if (resultCode == RESULT_OK && imageData != null) {
                        imageUri = imageData.data
                        Picasso.get().load(imageUri).into(addUserBinding.imageViewProfile)
                    }
                })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            chooseImage()
        } else {
            Toast.makeText(applicationContext, "Please grant permission", Toast.LENGTH_SHORT).show()
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1
            )
        }
    }

    fun chooseImage() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1
            )
        } else {
            val imageIntent = Intent()
            imageIntent.action = Intent.ACTION_GET_CONTENT
            imageIntent.type = "image/*"

            activityResultLauncher.launch(imageIntent)
        }
    }

    fun addUserToDatabase(url:String, imageId:String) {
        val name: String = addUserBinding.editTextName.text.toString()
        val email: String = addUserBinding.editTextEmail.text.toString()
        val age: Int = addUserBinding.editTextAge.text.toString().toInt()
        val id = reference.push().key as String
        val user = User(id, name, age, email, url,imageId)
        reference.child(id).setValue(user).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(applicationContext, "User added successfully", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(applicationContext, "Failed to add new user", Toast.LENGTH_SHORT)
                    .show()
            }
            addUserBinding.buttonAdd.isClickable = true
            addUserBinding.progressBar.visibility = View.INVISIBLE
            finish()
        }
    }

    fun uploadPhoto() {
        addUserBinding.buttonAdd.isClickable = false
        addUserBinding.progressBar.visibility = View.VISIBLE
        val imageId = UUID.randomUUID().toString()
        val imageReference = firebaseStorageReference.child("images").child(imageId)

        imageUri?.let {
            imageReference.putFile(it).addOnSuccessListener {
                Toast.makeText(applicationContext, "Image uploaded", Toast.LENGTH_SHORT).show()
                val myUploadedImageReference = firebaseStorageReference.child("images").child(imageId)
                myUploadedImageReference.downloadUrl.addOnSuccessListener { url->
                    val imageUrl = url.toString()
                    addUserToDatabase(imageUrl, imageId)
                }.addOnFailureListener{error->
                    Toast.makeText(applicationContext, error.localizedMessage, Toast.LENGTH_LONG).show()
                }

            }.addOnFailureListener {
                Toast.makeText(applicationContext, "Image upload failed", Toast.LENGTH_LONG).show()

            }
        }
    }
}