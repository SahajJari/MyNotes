package com.sahaj.mynotes.data.repository

import androidx.lifecycle.LiveData
import com.sahaj.mynotes.data.db.Note
import com.sahaj.mynotes.data.db.NoteDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository(
    private val db: NoteDB
) {

    suspend fun addNote(note: Note) = withContext(Dispatchers.IO) {
        db.getNoteDao().addNote(note)
    }

    suspend fun getNotes(): LiveData<List<Note>> = withContext(Dispatchers.IO) {
        db.getNoteDao().getNotes()
    }

    suspend fun updateNote(note: Note) = withContext(Dispatchers.IO) {
        db.getNoteDao().updateNote(note)
    }

    suspend fun deleteNote(note: Note) = withContext(Dispatchers.IO) {
        db.getNoteDao().deleteNote(note)
    }

}