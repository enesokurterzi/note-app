package com.example.domain.use_case.user

import com.example.data.repository.user.UserRepository
import com.google.firebase.auth.FirebaseUser

class GetUserUseCase(
    private val repository: UserRepository
) {

    operator fun invoke(): FirebaseUser? {
        return repository.getUser()
    }
}