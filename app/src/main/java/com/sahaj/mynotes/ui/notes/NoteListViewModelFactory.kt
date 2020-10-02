package com.sahaj.mynotes.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sahaj.mynotes.data.repository.NoteRepository

@Suppress("UNCHECKED_CAST")
class NoteListViewModelFactory(
    private val repository: NoteRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteListViewModel(repository) as T
    }

}