package com.example.noteapp.feature_note.domain.model

import com.example.noteapp.ui.theme.BabyBlue
import com.example.noteapp.ui.theme.LightGreen
import com.example.noteapp.ui.theme.RedOrange
import com.example.noteapp.ui.theme.RedPink
import com.example.noteapp.ui.theme.Violet


data class Note(
    val title: String = "",
    val content: String = "",
    val timestamp: Long = 0,
    val color: Int = 0,
    val id: String? = ""
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String): Exception(message)
