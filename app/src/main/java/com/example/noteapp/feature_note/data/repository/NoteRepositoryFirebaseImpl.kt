package com.example.noteapp.feature_note.data.repository

import com.example.noteapp.feature_note.domain.model.Note
import com.example.noteapp.feature_note.domain.repository.NoteRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

class NoteRepositoryFirebaseImpl(
    private val collectionReference: CollectionReference
) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return collectionReference.dataObjects<Note>()
    }

    override suspend fun getNoteById(id: String): Note? {
        var note: Note? = null
        collectionReference.document(id).get().addOnSuccessListener {
            note = it.toObject<Note>()
        }.await()
        return note
    }

    override suspend fun insertNote(note: Note) {
        if (note.id != null) {
            collectionReference.document(note.id).set(note).await()
        } else {
            with(collectionReference.document()) {
                set(note.copy(id = id)).await()
            }
        }

    }

    override suspend fun deleteNote(note: Note) {
        note.id?.let { collectionReference.document(it).delete().await() }
    }
}