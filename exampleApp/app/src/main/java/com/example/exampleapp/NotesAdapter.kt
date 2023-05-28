package com.example.exampleapp;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.exampleapp.entity.Note

class NotesAdapter (
    context: Context,
    private val listener: OnNoteSelected
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notes = emptyList<Note>() // Cached copy of notes

    override fun getItemCount() = notes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = inflater.inflate(R.layout.note_row, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.noteItemView.text = note.title

        if(note.date != null) {
            val date =  holder.itemView.findViewById<TextView>(R.id.reminderDateText)
            date.text = note.date
            date.visibility = View.VISIBLE
        }

        holder.itemView.findViewById<CardView>(R.id.noteCard).setOnClickListener(View.OnClickListener {
                view -> listener.onNoteSelected(note.mId!!) })
        val button = holder.itemView.findViewById<ImageButton>(R.id.deleteNote1);
        button.setOnClickListener(View.OnClickListener {
                view -> listener.deleteNote(note)
        })

    }

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }


    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       val noteItemView: TextView = itemView.findViewById(R.id.textView)
    }
}

