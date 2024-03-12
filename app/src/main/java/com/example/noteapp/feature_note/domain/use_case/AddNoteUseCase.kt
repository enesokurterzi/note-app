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
    suspend operator fun invoke(note: Note, resources: Resources) {
        if (note.title.isBlank()) {
            throw InvalidNoteException(resources.getString(R.string.save_note_error_title))
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException(resources.getString(R.string.save_note_error_content))
        }
        repository.insertNote(note)
    }
}