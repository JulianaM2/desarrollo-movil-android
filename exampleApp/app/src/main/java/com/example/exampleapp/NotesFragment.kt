package com.example.exampleapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exampleapp.dao.NotesDao
import com.example.exampleapp.entity.Note


/**
 * A simple [Fragment] subclass.
 * Use the [NotesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotesFragment : Fragment(), OnNoteSelected {
    private lateinit var notesDao: NotesDao
    private lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_notes, container, false)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerview)
        adapter = NotesAdapter(requireContext(), this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        val database = MyDatabase.getInstance(requireContext())
        notesDao = database.notesDao()
        return rootView
    }

    override fun onResume() {
        super.onResume()
        showNotes()
    }

    fun showNotes() {
        val notes = notesDao?.getNotes()
        if (notes != null) {
            val newNotes = notes.filter { note -> !note.reminder!! }
            adapter.setNotes(newNotes)
        }
    }

    override fun onNoteSelected(id: Int) {
        val intent = Intent(context, EditActivity::class.java)
        val note = notesDao?.getNote(id)
        intent.putExtra("id", note.mId)
        intent.putExtra("title", note.title)
        startActivity(intent)
    }

    override fun deleteNote(note: Note) {
        notesDao.delete(note)
        showNotes()
    }
}