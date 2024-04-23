package com.example.domain.use_case.user

import com.example.data.repository.user.UserRepository
import com.example.model.User

class LoginUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        checkEmailPassword(user = user)
        repository.login(user)
    }
}