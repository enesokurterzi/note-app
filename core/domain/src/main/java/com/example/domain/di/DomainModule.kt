package com.example.domain.di

import com.example.data.repository.note.NoteRepository
import com.example.data.repository.user.GoogleUserRepository
import com.example.data.repository.user.UserRepository
import com.example.domain.use_case.note.*
import com.example.domain.use_case.user.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DomainModule {
    @Provides
    @Singleton
    fun provideUserUseCases(repository: UserRepository, googleRepository: GoogleUserRepository): UserUseCases {
        return UserUseCases(
            loginUseCase = LoginUseCase(repository),
            signUpUseCase = SignUpUseCase(repository),
            signOutUseCase = SignOutUseCase(repository),
            getUserUseCase = GetUserUseCase(repository),
            signInWithGoogleUseCase = SignInWithGoogleUseCase(googleRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository),
            addNoteUseCase = AddNoteUseCase(repository),
            getNoteUseCase = GetNoteUseCase(repository),
            checkNoteUseCase = CheckNoteUseCase()
        )
    }
}