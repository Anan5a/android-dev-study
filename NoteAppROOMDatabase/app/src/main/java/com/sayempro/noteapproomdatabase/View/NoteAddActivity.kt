package com.sayempro.noteapproomdatabase.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.sayempro.noteapproomdatabase.R

class NoteAddActivity : AppCompatActivity() {
    lateinit var etNoteTitle:EditText
    lateinit var etNoteDescription:EditText
    lateinit var cancelButton:Button
    lateinit var saveButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_add)

        supportActionBar?.title = "Add note"

        etNoteTitle = findViewById(R.id.editTextNoteTitle)
        etNoteDescription = findViewById(R.id.editTextNoteDescription)
        cancelButton = findViewById(R.id.buttonCancel)
        saveButton = findViewById(R.id.buttonSave)


        cancelButton.setOnClickListener {
            finish()
            Toast.makeText(applicationContext, "Note was not saved", Toast.LENGTH_SHORT).show()
        }
        saveButton.setOnClickListener {
            saveNote()
            finish()
        }

    }


    fun saveNote(){
        val title = etNoteTitle.text.toString()
        val description = etNoteDescription.text.toString()

        val intent = Intent()
        intent.putExtra("noteTitle", title)
        intent.putExtra("noteDesc", description)
        setResult(RESULT_OK, intent)
    }
}