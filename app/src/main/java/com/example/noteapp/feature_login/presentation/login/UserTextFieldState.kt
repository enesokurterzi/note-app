package com.example.noteapp.feature_login.presentation.login

import com.example.noteapp.R

data class UserTextFieldState(
    val text: String = "",
    val error: Int = R.string.blank,
    val isErrorVisible: Boolean = false
)
