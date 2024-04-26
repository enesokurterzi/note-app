package com.example.domain.use_case.user

import android.content.Context
import com.example.data.repository.user.GoogleUserRepository
import java.security.MessageDigest
import java.util.UUID

class SignInWithGoogleUseCase(
    private val repository: GoogleUserRepository
) {
    private val rawNonce = UUID.randomUUID().toString()
    private val bytes = rawNonce.toByteArray()
    private val md = MessageDigest.getInstance("SHA-256")
    private val digest = md.digest(bytes)
    private val hashedNonce = digest.fold("") { str, it -> str + "%02x".format(it)} // To improve sign-in security and avoid replay attacks.
    suspend operator fun invoke(context: Context) {
        repository.signInWithGoogle(context, hashedNonce)
    }
}