package com.sayempro.noteapproomdatabase

import android.app.Application
import com.sayempro.noteapproomdatabase.Repository.NoteRepository
import com.sayempro.noteapproomdatabase.Room.NoteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NoteApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { NoteDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { NoteRepository(database.getNoteDao()) }
}