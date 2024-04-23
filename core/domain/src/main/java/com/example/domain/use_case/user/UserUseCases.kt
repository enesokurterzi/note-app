package com.example.domain.use_case.user

import android.util.Patterns
import com.example.model.InvalidEmailException
import com.example.model.InvalidPasswordException
import com.example.model.User

data class UserUseCases (
    val loginUseCase: LoginUseCase,
    val signUpUseCase: SignUpUseCase,
    val signOutUseCase: SignOutUseCase,
    val getUserUseCase: GetUserUseCase
)

@Throws(InvalidEmailException::class, InvalidPasswordException::class)
fun checkEmailPassword(user: User) {
    if (!Patterns.EMAIL_ADDRESS.matcher(user.email).matches()) {
        throw InvalidEmailException()
    }
    if (user.password.length < 6) {
        throw InvalidPasswordException()
    }
}