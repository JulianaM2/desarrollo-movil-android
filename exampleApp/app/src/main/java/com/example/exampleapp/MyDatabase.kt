package com.example.exampleapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.exampleapp.dao.NotesDao
import com.example.exampleapp.entity.Note

@Database(entities = [Note::class], version = 2)
abstract class MyDatabase: RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {
        private var INSTANCE: MyDatabase? = null
        private var sLock = Any()

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE notes\n" +
                        "ADD date varchar(50) DEFAULT NULL")
            }
        }


        fun getInstance(context: Context): MyDatabase {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder<MyDatabase>(
                        context.applicationContext,
                        MyDatabase::class.java, "Sample.db"
                    ).allowMainThreadQueries().addMigrations(MIGRATION_1_2)
                        .build()
                }
                return INSTANCE as MyDatabase
            }
        }
    }

}