package com.sahaj.mynotes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.redbox.util.RVGridSpacing
import com.sahaj.mynotes.R
import com.sahaj.mynotes.data.db.NoteDB
import kotlinx.android.synthetic.main.frag_note_list.*
import kotlinx.coroutines.launch

class NoteListFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.frag_note_list, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init()

    }

    private fun init() {

        rcv_notes.setHasFixedSize(true)
        rcv_notes.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        rcv_notes.addItemDecoration(RVGridSpacing(2, 20, true))

        launch {

            context?.let {

                val notes = NoteDB(it).getNoteDao().getNotes()

                rcv_notes.adapter = NoteAdapter(notes)

            }

        }

        setOnClickListener()

    }

    private fun setOnClickListener() {

        btnAdd.setOnClickListener {

            val navigation = NoteListFragmentDirections.actionAddNote()
            Navigation.findNavController(it).navigate(navigation)

        }

    }

}