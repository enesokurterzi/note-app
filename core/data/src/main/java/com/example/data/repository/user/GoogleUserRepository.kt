package com.example.data.repository.user

import android.content.Context

interface GoogleUserRepository {

    suspend fun signInWithGoogle(context: Context, hashedNonce: String)
}