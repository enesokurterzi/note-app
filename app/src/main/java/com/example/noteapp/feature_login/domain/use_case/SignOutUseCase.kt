package com.example.noteapp.feature_login.domain.use_case

import com.example.noteapp.feature_login.domain.repository.UserRepository

class SignOutUseCase(
    private val repository: UserRepository
) {
    operator fun invoke() {
        repository.signOut()
    }

}