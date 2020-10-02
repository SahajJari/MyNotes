package com.sahaj.mynotes.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sahaj.mynotes.R
import com.sahaj.mynotes.data.db.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(private val notes: List<Note>) :
    RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        )

    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemView.tvntitle.text = notes[position].title
        holder.itemView.tvntext.text = notes[position].text

        holder.itemView.setOnClickListener {

            val action = NoteListFragmentDirections.actionAddNote(notes[position])
            Navigation.findNavController(it).navigate(action)

        }

    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)


}