package com.example.noteapp.feature_note.presentation.add_edit_note

import com.example.noteapp.R

data class NoteTextFieldState(
    val text: String = "",
    val hint: Int = R.string.blank,
    val isHintVisible: Boolean = true
)
