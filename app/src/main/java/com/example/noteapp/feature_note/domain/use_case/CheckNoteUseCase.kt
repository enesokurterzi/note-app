package com.example.noteapp.feature_note.domain.use_case

import com.example.noteapp.R
import com.example.noteapp.feature_note.domain.model.InvalidNoteException
import com.example.noteapp.feature_note.domain.model.Note

class CheckNoteUseCase {

    @Throws(InvalidNoteException::class)
    operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException(R.string.save_note_error_title.toString())
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException(R.string.save_note_error_content.toString())
        }
    }

}