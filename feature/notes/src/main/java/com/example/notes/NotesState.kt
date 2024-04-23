package com.example.notes

import com.example.domain.util.NoteOrder
import com.example.domain.util.OrderType
import com.example.model.Note

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
