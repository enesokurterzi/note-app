package com.example.domain.use_case.note

import com.example.data.repository.note.NoteRepository
import com.example.model.Note

class GetNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: String): Note? {
        return repository.getNoteById(id)
    }
}