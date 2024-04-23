package com.example.notes

import com.example.domain.util.NoteOrder
import com.example.model.Note

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder) : NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    data object RestoreNote : NotesEvent()
    data object ToggleOrderSection : NotesEvent()
    data object LogOut: NotesEvent()
}