package com.example.exampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DisplayNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_note)

        val note = intent.getStringExtra("nota1")
        val textView = findViewById<TextView>(R.id.nota1)
        textView.text = note
    }
}