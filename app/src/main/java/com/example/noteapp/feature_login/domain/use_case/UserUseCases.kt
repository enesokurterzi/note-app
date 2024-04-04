package com.example.noteapp.feature_login.domain.use_case

import android.util.Patterns
import com.example.noteapp.feature_login.domain.model.InvalidEmailException
import com.example.noteapp.feature_login.domain.model.InvalidPasswordException
import com.example.noteapp.feature_login.domain.model.User

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