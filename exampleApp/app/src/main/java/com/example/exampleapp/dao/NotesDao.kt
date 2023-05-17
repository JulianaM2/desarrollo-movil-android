package com.example.exampleapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exampleapp.entity.Note

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes")
    abstract fun getNotes(): List<Note>

    @Query("SELECT * FROM notes WHERE _id = :noteId")
    abstract fun getNote(noteId:Int): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertNote(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("Delete from notes")
    fun deleteAllNotes()
}