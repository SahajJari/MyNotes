package com.sahaj.mynotes.ui.notes

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.sahaj.mynotes.data.repository.NoteRepository
import com.sahaj.mynotes.utils.lazyDeffered


class NoteListViewModel(private val repository: NoteRepository) : ViewModel() {

    val notes by lazyDeffered {
        repository.getNotes()
    }

    fun onAddClick(view: View) {

        val navigation = NoteListFragmentDirections.actionAddNote(null)
        Navigation.findNavController(view).navigate(navigation)

    }

}