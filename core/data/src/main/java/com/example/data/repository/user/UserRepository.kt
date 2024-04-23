package com.example.data.repository.user

import com.example.model.User
import com.google.firebase.auth.FirebaseUser

interface UserRepository {

    suspend fun login(user: User)

    suspend fun signUp(user: User)

    fun signOut()

    fun getUser(): FirebaseUser?
}