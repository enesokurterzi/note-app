package com.example.NoteApp.models

data class Note (
    val id: Int,
    val title: String,
    val content: String,
    val date: String
        )

data class Notes(
    val notes: MutableList<Note>
)