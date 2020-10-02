package com.sahaj.mynotes.ui.addnotes

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.sahaj.mynotes.R
import com.sahaj.mynotes.data.db.Note
import com.sahaj.mynotes.databinding.FragAddNoteBinding
import com.sahaj.mynotes.ui.BaseFragment
import com.sahaj.mynotes.utils.showToast
import kotlinx.android.synthetic.main.frag_add_note.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class AddNoteFragment : BaseFragment(), KodeinAware {

    override val kodein by kodein()

    private val factory: AddNoteViewModelFactory by instance()

    private lateinit var viewModel: AddNoteViewModel

    /*

        1) jvm 1.8 is required in gradle.build :

            android {
                // Other code here...
                kotlinOptions {
                    jvmTarget = "1.8"
                }
            }

        2)  We can use this way now also

     */
//    private val viewModel: AddNoteViewModel by viewModels { factory }

    private var note: Note? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragAddNoteBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, factory).get(AddNoteViewModel::class.java)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init()

    }

    private fun init() {

        arguments?.let {

            note = AddNoteFragmentArgs.fromBundle(it).note

            note?.let {
                setHasOptionsMenu(true)
            }

            viewModel.noteTitle = note?.title
            viewModel.noteText = note?.text

        }

        setOnClickListener()

    }

    private fun setOnClickListener() {

        btnSave.setOnClickListener { view ->

            val title = viewModel.noteTitle!!.trim()
            val text = viewModel.noteText!!.trim()

            if (title.isEmpty()) {

                etTitle.error = "Title Required"
                etTitle.requestFocus()

                return@setOnClickListener

            }

            if (text.isEmpty()) {

                etText.error = "Text Note Required"
                etText.requestFocus()
                return@setOnClickListener

            }

            viewModel.saveNote(view, note)

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.delete -> {

                if (note != null) showDeleteDialog(note!!)
                else context?.showToast("Can't Delete Empty Note")

            }

        }

        return super.onOptionsItemSelected(item)
    }

    private fun showDeleteDialog(note: Note) {

        AlertDialog.Builder(requireContext()).apply {

            setTitle("Confirm Delete")
            setMessage("Are you sure to delete this note ? It can't be undone.")

            setPositiveButton("Yes") { _, _ ->

                viewModel.deleteNote(note)

            }

            setNegativeButton("No") { _, _ ->

            }

        }.create().show()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu, menu)

    }

}