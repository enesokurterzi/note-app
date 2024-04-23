package com.example.model

import androidx.compose.ui.graphics.Color
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

val RedOrange = Color(0xffffab91)
val RedPink = Color(0xfff48fb1)
val BabyBlue = Color(0xff81deea)
val Violet = Color(0xffcf94da)
val LightGreen = Color(0xffe7ed9b)

class InvalidNoteException(message: String): Exception(message)
