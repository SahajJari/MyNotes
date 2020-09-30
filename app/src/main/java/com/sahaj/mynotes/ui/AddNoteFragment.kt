package com.sahaj.mynotes.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import com.sahaj.mynotes.R
import com.sahaj.mynotes.data.db.Note
import com.sahaj.mynotes.data.db.NoteDB
import com.sahaj.mynotes.utils.showToast
import kotlinx.android.synthetic.main.frag_add_note.*
import kotlinx.coroutines.launch

class AddNoteFragment : BaseFragment() {

    private var note: Note? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.frag_add_note, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init()

    }

    private fun init() {

        arguments?.let {

            note = AddNoteFragmentArgs.fromBundle(it).note

            etTitle.setText(note?.title)
            etText.setText(note?.text)

            setHasOptionsMenu(true)

        }

        setOnClickListener()

    }

    private fun setOnClickListener() {

        btnSave.setOnClickListener { view ->

            val title = etTitle.text.toString().trim()
            val text = etText.text.toString().trim()

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

            launch {

                val mNote = Note(title, text)

                context?.let {

                    if (note == null)
                        NoteDB(it).getNoteDao().addNote(mNote)
                    else {
                        mNote.id = note!!.id
                        NoteDB(it).getNoteDao().updateNote(mNote)
                    }


                    val navigation = AddNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(view).navigate(navigation)

                }

            }

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

                launch {

                    NoteDB(context).getNoteDao().deleteNote(note)

                    val action = AddNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(requireView()).navigate(action)

                }

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