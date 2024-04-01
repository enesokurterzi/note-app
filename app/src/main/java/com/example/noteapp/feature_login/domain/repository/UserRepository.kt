package com.example.noteapp.feature_login.domain.repository

import com.example.noteapp.feature_login.domain.model.User

interface UserRepository {

    suspend fun login(user: User)

    suspend fun signUp(user: User)

    fun signOut()
}