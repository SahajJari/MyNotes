package com.sahaj.mynotes.ui.addnotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sahaj.mynotes.data.repository.NoteRepository

@Suppress("UNCHECKED_CAST")
class AddNoteViewModelFactory(
    private val repository: NoteRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddNoteViewModel(repository) as T
    }

}