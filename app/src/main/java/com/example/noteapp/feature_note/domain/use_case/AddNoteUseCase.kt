package com.example.noteapp.feature_note.domain.use_case

import com.example.noteapp.feature_note.domain.model.Note
import com.example.noteapp.feature_note.domain.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository,
    private val checkNoteUseCase: CheckNoteUseCase
) {

    suspend operator fun invoke(note: Note) {
        checkNoteUseCase.invoke(note)
        repository.insertNote(note)
    }
}