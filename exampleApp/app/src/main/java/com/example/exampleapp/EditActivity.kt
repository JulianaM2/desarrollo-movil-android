package com.example.exampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Switch
import com.example.exampleapp.dao.NotesDao
import com.example.exampleapp.entity.Note

class EditActivity : AppCompatActivity() {
    private lateinit var notesDao: NotesDao
    private lateinit var note: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val database = MyDatabase.getInstance(this)
        notesDao = database.notesDao()

        val noteId = intent.getIntExtra("id", -1)

        if(noteId != -1){
            note = notesDao.getNote(noteId)
            findViewById<EditText>(R.id.edit_message).setText(note.title)
            findViewById<Switch>(R.id.isReminder).isChecked = note.reminder == true
        }
    }

    fun saveNote(view: View) {
        if(!this::note.isInitialized){
            note = Note()
        }

        note.title = findViewById<EditText>(R.id.edit_message).text.toString()
        note.reminder = findViewById<Switch>(R.id.isReminder).isChecked
        notesDao.insertNote(note)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}