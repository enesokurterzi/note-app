package com.example.data.di

import com.example.data.repository.note.NoteRepository
import com.example.data.repository.note.NoteRepositoryFirebaseImpl
import com.example.data.repository.user.UserRepository
import com.example.data.repository.user.UserRepositoryImpl
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @Provides
    @Singleton
    fun provideCollection(firebaseAuth: FirebaseAuth): CollectionReference {
        return Firebase.firestore.collection("notes").document(firebaseAuth.currentUser!!.uid)
            .collection("my_notes")
    }

    @Provides
    @Singleton
    fun provideNoteRepository(collectionReference: CollectionReference): NoteRepository {
        return NoteRepositoryFirebaseImpl(collectionReference)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideUserRepository(firebaseAuth: FirebaseAuth): UserRepository {
        return UserRepositoryImpl(firebaseAuth)
    }
}