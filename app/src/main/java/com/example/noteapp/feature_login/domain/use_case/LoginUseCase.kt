package com.example.noteapp.feature_login.domain.use_case

import com.example.noteapp.feature_login.domain.model.User
import com.example.noteapp.feature_login.domain.repository.UserRepository

class LoginUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        checkEmailPassword(user = user)
        repository.login(user)
    }
}