package com.example.noteapp.feature_login.presentation.login

sealed class LoginEvent {
    data class EnteredEmail(val value: String) : LoginEvent()
    data class EnteredPassword(val value: String) : LoginEvent()

    data object Login : LoginEvent()

    data object SignUp : LoginEvent()

}