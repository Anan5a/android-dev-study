package com.sayempro.noteapproomdatabase.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.sayempro.noteapproomdatabase.R

class UpdateActivity : AppCompatActivity() {
    lateinit var etNoteTitle: EditText
    lateinit var etNoteDescription: EditText
    lateinit var cancelButton: Button
    lateinit var saveButton: Button
    var currentId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        supportActionBar?.title = "Update note"

        etNoteTitle = findViewById(R.id.editTextNoteTitleUpdate)
        etNoteDescription = findViewById(R.id.editTextNoteDescriptionUpdate)
        cancelButton = findViewById(R.id.buttonCancelUpdate)
        saveButton = findViewById(R.id.buttonSaveUpdate)
        getAndSetData()

        cancelButton.setOnClickListener {
            finish()
            Toast.makeText(applicationContext, "Note was not changed", Toast.LENGTH_SHORT).show()
        }
        saveButton.setOnClickListener {
            updateNote()
            finish()
        }

    }


    fun updateNote() {
        val title = etNoteTitle.text.toString()
        val description = etNoteDescription.text.toString()

        val intent = Intent()
        intent.putExtra("noteTitleUpdated", title)
        intent.putExtra("noteDescUpdated", description)
        if (currentId != -1){
            intent.putExtra("currentId", currentId)
            setResult(RESULT_OK, intent)
            finish()
        }

    }

    fun getAndSetData() {

        val currentTitle = intent.getStringExtra("currentTitle")
        val currentDesc = intent.getStringExtra("currentDesc")
        currentId = intent.getIntExtra("currentId", -1)

        etNoteTitle.setText(currentTitle)
        etNoteDescription.setText(currentDesc)
    }
}