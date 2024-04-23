package com.example.login

data class UserTextFieldState(
    val text: String = "",
    val error: Int = R.string.blank,
    val isErrorVisible: Boolean = false
)
