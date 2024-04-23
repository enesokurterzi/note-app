package com.example.domain.use_case.user

import com.example.data.repository.user.UserRepository

class SignOutUseCase(
    private val repository: UserRepository
) {
    operator fun invoke() {
        repository.signOut()
    }

}