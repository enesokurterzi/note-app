package com.example.domain.use_case.user

import com.example.data.repository.user.UserRepository
import com.example.model.InvalidEmailException
import com.example.model.InvalidPasswordException
import com.example.model.User

class SignUpUseCase(
    private val repository: UserRepository
) {

    @Throws(InvalidEmailException::class, InvalidPasswordException::class)
    suspend operator fun invoke(user: User) {
        checkEmailPassword(user = user)
        repository.signUp(user = user)
    }
}