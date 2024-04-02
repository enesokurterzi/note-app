package com.example.noteapp.di

import com.example.noteapp.feature_login.data.repository.UserRepositoryImpl
import com.example.noteapp.feature_login.domain.repository.UserRepository
import com.example.noteapp.feature_login.domain.use_case.LoginUseCase
import com.example.noteapp.feature_login.domain.use_case.SignOutUseCase
import com.example.noteapp.feature_login.domain.use_case.SignUpUseCase
import com.example.noteapp.feature_login.domain.use_case.UserUseCases
import com.example.noteapp.feature_note.data.repository.NoteRepositoryFirebaseImpl
import com.example.noteapp.feature_note.domain.repository.NoteRepository
import com.example.noteapp.feature_note.domain.use_case.AddNoteUseCase
import com.example.noteapp.feature_note.domain.use_case.DeleteNoteUseCase
import com.example.noteapp.feature_note.domain.use_case.GetNoteUseCase
import com.example.noteapp.feature_note.domain.use_case.GetNotesUseCase
import com.example.noteapp.feature_note.domain.use_case.NoteUseCases
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
object AppModule {

//    @Provides
//    @Singleton
//    fun provideNoteDatabase(app: Application): NoteDataBase {
//        return Room.databaseBuilder(
//            app,
//            NoteDataBase::class.java,
//            NoteDataBase.DATABASE_NAME
//        ).build()
//    }

    @Provides
    @Singleton
    fun provideNoteRepository(collectionReference: CollectionReference): NoteRepository {
//        return NoteRepositoryImpl(db.noteDao)
        return NoteRepositoryFirebaseImpl(collectionReference)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository),
            addNoteUseCase = AddNoteUseCase(repository),
            getNoteUseCase = GetNoteUseCase(repository)
        )
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

    @Provides
    @Singleton
    fun provideUserUseCases(repository: UserRepository): UserUseCases {
        return UserUseCases(
            loginUseCase = LoginUseCase(repository),
            signUpUseCase = SignUpUseCase(repository),
            signOutUseCase = SignOutUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideCollection(firebaseAuth: FirebaseAuth): CollectionReference {
        return Firebase.firestore.collection("notes").document(firebaseAuth.currentUser!!.uid)
            .collection("my_notes")
    }

}