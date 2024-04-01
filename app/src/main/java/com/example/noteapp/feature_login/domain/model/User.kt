package com.example.noteapp.feature_login.domain.model

data class User(
    val email: String,
    val password: String
)

class InvalidEmailException: Exception()

class InvalidPasswordException: Exception()

class NotVerifiedEmailException: Exception()
