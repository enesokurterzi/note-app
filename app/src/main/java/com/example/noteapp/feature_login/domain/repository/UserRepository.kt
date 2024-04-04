package com.example.noteapp.feature_login.domain.repository

import com.example.noteapp.feature_login.domain.model.User
import com.google.firebase.auth.FirebaseUser

interface UserRepository {

    suspend fun login(user: User)

    suspend fun signUp(user: User)

    fun signOut()

    fun getUser(): FirebaseUser?
}