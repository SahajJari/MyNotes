package com.sahaj.mynotes.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.redbox.util.RVGridSpacing
import com.sahaj.mynotes.data.db.Note
import com.sahaj.mynotes.databinding.FragNoteListBinding
import com.sahaj.mynotes.ui.BaseFragment
import kotlinx.android.synthetic.main.frag_note_list.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class NoteListFragment : BaseFragment(), KodeinAware {

    override val kodein by kodein()

    private val factory: NoteListViewModelFactory by instance()

    private lateinit var viewModel: NoteListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragNoteListBinding.inflate(inflater, container, false)

        viewModel = ViewModelProviders.of(this, factory).get(NoteListViewModel::class.java)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init()

    }

    private fun init() {

        launch {

            viewModel.notes.await().observe(viewLifecycleOwner, Observer {

                initRecyclerView(it)

            })

        }

    }

    private fun initRecyclerView(it: List<Note>) {

        rcv_notes.apply {

            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(RVGridSpacing(2, 20, true))

            adapter = NoteAdapter(it)

        }

    }

}