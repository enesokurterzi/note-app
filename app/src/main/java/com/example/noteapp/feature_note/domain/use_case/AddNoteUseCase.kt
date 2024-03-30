package com.example.noteapp.feature_note.domain.use_case

import android.content.res.Resources
import com.example.noteapp.R
import com.example.noteapp.feature_note.domain.model.InvalidNoteException
import com.example.noteapp.feature_note.domain.model.Note
import com.example.noteapp.feature_note.domain.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException(R.string.save_note_error_title.toString())
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException(R.string.save_note_error_content.toString())
        }
        repository.insertNote(note)
    }
}