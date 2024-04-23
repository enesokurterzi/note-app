package com.example.data.repository.note

import com.example.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: String): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}