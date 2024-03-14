package com.sayempro.firebase

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
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
import com.sayempro.firebase.databinding.ActivityUpdateUserBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UpdateUserActivity : AppCompatActivity() {
    lateinit var updateUserBinding: ActivityUpdateUserBinding
    val database = Firebase.database
    val reference = database.reference.child("MyUsers")
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    var imageUri: Uri? = null
    val firebaseStorage = Firebase.storage
    val firebaseStorageReference = firebaseStorage.reference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateUserBinding = ActivityUpdateUserBinding.inflate(layoutInflater)
        val view = updateUserBinding.root
        setContentView(view)
        supportActionBar?.title = "Update user"
        registerForActivityForResult()


        populateEditFields()
        updateUserBinding.imageViewProfileUpdate.setOnClickListener {
            chooseImage()
        }
        val imageId: String = intent.getStringExtra("imageId") ?: ""


        updateUserBinding.buttonUpdate.setOnClickListener {

            uploadPhoto(imageId)
        }

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

    fun populateEditFields() {
        val email: String = intent.getStringExtra("email") ?: ""
        val name: String = intent.getStringExtra("name") ?: ""
        val url: String = intent.getStringExtra("url") ?: ""
        val age: Int = intent.getIntExtra("age", -1)

        Picasso.get().load(url).into(updateUserBinding.imageViewProfileUpdate, object : Callback {
            override fun onSuccess() {
            }

            override fun onError(e: Exception?) {
                Toast.makeText(applicationContext, e?.localizedMessage, Toast.LENGTH_LONG).show()
            }

        })
        updateUserBinding.editTextAgeUpdate.setText(age.toString())
        updateUserBinding.editTextNameUpdate.setText(name)
        updateUserBinding.editTextEmailUpdate.setText(email)
    }

    fun updateData(newImageUrl: String, imageId:String) {
        val email: String = updateUserBinding.editTextEmailUpdate.text.toString()
        val name: String = updateUserBinding.editTextNameUpdate.text.toString()
        val age: Int = updateUserBinding.editTextAgeUpdate.text.toString().toInt()
        val id: String = intent.getStringExtra("id") ?: ""

        val user = mapOf<String, Any>(
            "userId" to id,
            "userName" to name,
            "userAge" to age,
            "userEmail" to email,
            "profileImageId" to imageId,
            "profileImage" to newImageUrl
        )

        reference.child(id).updateChildren(user).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(applicationContext, "User updated successfully", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(applicationContext, "User update failed", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
//        firebaseStorageReference.child("images").child(imageId).delete()
    }

    fun registerForActivityForResult() {
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
                ActivityResultCallback { result ->
                    val resultCode = result.resultCode
                    val imageData = result.data
                    if (resultCode == RESULT_OK && imageData != null) {
                        imageUri = imageData.data
                        Picasso.get().load(imageUri).into(updateUserBinding.imageViewProfileUpdate)
                    }
                })
    }

    fun uploadPhoto(imageId: String) {
        updateUserBinding.buttonUpdate.isClickable = false
//        updateUserBinding.progressBar.visibility = View.VISIBLE
        val imageReference = firebaseStorageReference.child("images").child(imageId)
        imageUri?.let {
            imageReference.putFile(it).addOnSuccessListener {
                Toast.makeText(applicationContext, "Image uploaded", Toast.LENGTH_SHORT).show()
                imageReference.downloadUrl.addOnSuccessListener { url ->
                    val newImageUrl = url.toString()

                    updateData(newImageUrl, imageId)


                }.addOnFailureListener { error ->
                    Toast.makeText(applicationContext, error.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                    updateUserBinding.buttonUpdate.isClickable = true

                }

            }.addOnFailureListener {
                Toast.makeText(applicationContext, "Image upload failed", Toast.LENGTH_LONG).show()
                updateUserBinding.buttonUpdate.isClickable = true

            }
        }
    }
}