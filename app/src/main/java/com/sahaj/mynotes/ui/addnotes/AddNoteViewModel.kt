package com.sahaj.mynotes.ui.addnotes

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.sahaj.mynotes.data.db.Note
import com.sahaj.mynotes.data.repository.NoteRepository
import com.sahaj.mynotes.utils.Coroutines

class AddNoteViewModel(
    private val repository: NoteRepository
) : ViewModel() {

    var noteTitle: String? = null
    var noteText: String? = null

    fun saveNote(view: View, note: Note?) {

        Coroutines.main {

            val mNote = Note(noteTitle!!, noteText!!)

            if (note == null)

                repository.addNote(mNote)
            else {

                mNote.id = note.id

                repository.updateNote(mNote)

            }

            val navigation = AddNoteFragmentDirections.actionSaveNote()
            Navigation.findNavController(view).navigate(navigation)

        }

    }

    fun deleteNote(note: Note) {

        Coroutines.main {
            repository.deleteNote(note)
        }

    }

}