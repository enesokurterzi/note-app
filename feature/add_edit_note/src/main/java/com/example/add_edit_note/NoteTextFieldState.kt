package com.example.add_edit_note

data class NoteTextFieldState(
    val text: String = "",
    val hint: Int = R.string.blank,
    val isHintVisible: Boolean = true
)
