package com.example.domain.use_case.note

import com.example.domain.R
import com.example.model.InvalidNoteException
import com.example.model.Note

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