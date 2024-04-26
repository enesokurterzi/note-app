package com.example.login

import android.content.Context

sealed class LoginEvent {
    data class EnteredEmail(val value: String) : LoginEvent()
    data class EnteredPassword(val value: String) : LoginEvent()
    data object Login : LoginEvent()
    data object SignUp : LoginEvent()
    data class SignInWithGoogle(val context: Context): LoginEvent()

}