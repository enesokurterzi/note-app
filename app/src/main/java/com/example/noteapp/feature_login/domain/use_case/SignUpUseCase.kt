package com.example.noteapp.feature_login.domain.use_case

import com.example.noteapp.feature_login.domain.model.InvalidEmailException
import com.example.noteapp.feature_login.domain.model.InvalidPasswordException
import com.example.noteapp.feature_login.domain.model.User
import com.example.noteapp.feature_login.domain.repository.UserRepository

class SignUpUseCase(
    private val repository: UserRepository
) {

    @Throws(InvalidEmailException::class, InvalidPasswordException::class)
    suspend operator fun invoke(user: User) {
        checkEmailPassword(user = user)
        repository.signUp(user = user)
    }
}