package com.sahaj.mynotes

import android.app.Application
import com.sahaj.mynotes.data.db.NoteDB
import com.sahaj.mynotes.data.repository.NoteRepository
import com.sahaj.mynotes.ui.addnotes.AddNoteViewModelFactory
import com.sahaj.mynotes.ui.notes.NoteListViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MyNotesApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {

        import(androidXModule(this@MyNotesApplication))

        bind() from singleton { NoteDB(instance()) }
        bind() from singleton { NoteRepository(instance()) }
        bind() from provider { NoteListViewModelFactory(instance()) }
        bind() from provider { AddNoteViewModelFactory(instance()) }

    }
}