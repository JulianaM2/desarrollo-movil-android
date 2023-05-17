package com.example.exampleapp.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
class Note {

    @PrimaryKey
    @ColumnInfo(name = "_id")
    var mId: Int? = null

    var title: String? = ""
    var reminder: Boolean? = false
}
