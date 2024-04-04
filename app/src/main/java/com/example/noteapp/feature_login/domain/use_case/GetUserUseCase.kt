package com.example.noteapp.feature_login.domain.use_case

import com.example.noteapp.feature_login.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseUser

class GetUserUseCase(
    private val repository: UserRepository
) {

    operator fun invoke(): FirebaseUser? {
        return repository.getUser()
    }
}