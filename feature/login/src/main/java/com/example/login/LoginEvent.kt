package com.example.login

sealed class LoginEvent {
    data class EnteredEmail(val value: String) : LoginEvent()
    data class EnteredPassword(val value: String) : LoginEvent()
    data object Login : LoginEvent()
    data object SignUp : LoginEvent()

}