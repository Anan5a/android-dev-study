package com.sayempro.noteapproomdatabase.View

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sayempro.noteapproomdatabase.Adapters.NoteAdapter
import com.sayempro.noteapproomdatabase.Model.Note
import com.sayempro.noteapproomdatabase.NoteApplication
import com.sayempro.noteapproomdatabase.R
import com.sayempro.noteapproomdatabase.ViewModel.NoteViewModel
import com.sayempro.noteapproomdatabase.ViewModel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel
    lateinit var addActivityResultLauncher: ActivityResultLauncher<Intent>
    lateinit var updateActivityResultLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelFactory = NoteViewModelFactory((application as NoteApplication).repository)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val noteAdapter = NoteAdapter(this)
        recyclerView.adapter = noteAdapter
        registerActivityResultLauncher()
        noteViewModel = ViewModelProvider(this, viewModelFactory).get(NoteViewModel::class.java)
        noteViewModel.myAllNotes.observe(this) { notes ->
            //update UI
            noteAdapter.setNote(notes)
        }

        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                noteViewModel.delete(noteAdapter.getNote(viewHolder.adapterPosition))
                Toast.makeText(applicationContext, "Note deleted", Toast.LENGTH_LONG).show()
            }

        }).attachToRecyclerView(recyclerView)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_note -> {
                val intent = Intent(this, NoteAddActivity::class.java)
                addActivityResultLauncher.launch(intent)
            }

            R.id.delete_all_notes -> {

                val alertDialog = AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to delete all notes?")
                    .setTitle("Delete everything?")
                    .setPositiveButton("Delete", DialogInterface.OnClickListener { dialog, which ->
                        noteViewModel.deleteAllNotes()

                        Toast.makeText(
                            applicationContext, "Everything is deleted", Toast.LENGTH_LONG
                        ).show()
                        dialog.dismiss()
                    }).setNegativeButton("Cancel",
                        DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
                    .setCancelable(false)
                alertDialog.create().show()
            }
        }
        return true
    }

    fun registerActivityResultLauncher() {
        addActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
                ActivityResultCallback { resultAddNote ->
                    val resultCode = resultAddNote.resultCode
                    val data = resultAddNote.data

                    if (resultCode == RESULT_OK && data != null) {
                        val noteTitle: String = data.getStringExtra("noteTitle").toString()
                        val noteDescription: String = data.getStringExtra("noteDesc").toString()

                        val note = Note(noteTitle, noteDescription)
                        noteViewModel.insert(note)
                    }

                })
        updateActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
                ActivityResultCallback { resultAddNote ->
                    val resultCode = resultAddNote.resultCode
                    val data = resultAddNote.data

                    if (resultCode == RESULT_OK && data != null) {
                        val noteTitle: String = data.getStringExtra("noteTitleUpdated").toString()
                        val noteDescription: String =
                            data.getStringExtra("noteDescUpdated").toString()
                        val noteId: Int = data.getIntExtra("currentId", -1)

                        val note = Note(noteTitle, noteDescription)
                        note.id = noteId
                        noteViewModel.update(note)
                    }

                })

    }
}