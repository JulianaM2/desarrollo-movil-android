package com.example.exampleapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exampleapp.dao.NotesDao
import com.example.exampleapp.entity.Note

@Database(entities = [Note::class], version = 1)
abstract class MyDatabase: RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {
        private var INSTANCE: MyDatabase? = null
        private var sLock = Any()

        fun getInstance(context: Context): MyDatabase {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder<MyDatabase>(
                        context.applicationContext,
                        MyDatabase::class.java, "Sample.db"
                    ).allowMainThreadQueries()
                        .build()
                }
                return INSTANCE as MyDatabase
            }
        }
    }

}