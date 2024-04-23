package com.example.domain.use_case.note

import com.example.data.repository.note.NoteRepository
import com.example.model.Note

class AddNoteUseCase(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        repository.insertNote(note)
    }
}