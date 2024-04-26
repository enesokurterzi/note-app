package com.example.data.repository.user

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import com.example.data.BuildConfig
import com.example.model.NotVerifiedEmailException
import com.example.model.User
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
) : UserRepository,GoogleUserRepository {
    override suspend fun login(user: User) {
        with(firebaseAuth) {
            signInWithEmailAndPassword(user.email, user.password).await()
            if (currentUser?.isEmailVerified != true) {
                signOut()
                throw NotVerifiedEmailException()
            }
        }

    }

    override suspend fun signUp(user: User) {
        with(firebaseAuth) {
            createUserWithEmailAndPassword(user.email, user.password).await()
            currentUser?.sendEmailVerification()
            signOut()
        }
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }

    override fun getUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    override suspend fun signInWithGoogle(context: Context, hashedNonce: String) {
        try {
            val credentialManager = CredentialManager.create(context)
            val request = getRequest(hashedNonce)
            val result = credentialManager.getCredential(
                request = request,
                context = context
            )
            val googleCredentials = getGoogleCredential(result)

            firebaseAuth.signInWithCredential(googleCredentials).await()
        } catch (e: GetCredentialException) {
            throw com.example.model.GetCredentialException()
        }

    }

    private fun getRequest(hashedNonce: String): GetCredentialRequest {
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(BuildConfig.WEB_CLIENT_ID)
            .setAutoSelectEnabled(true)
            .setNonce(hashedNonce)
            .build()

        return GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

    }

    private fun getGoogleCredential(result: GetCredentialResponse): AuthCredential {
        val credential = result.credential
        val googleIdTokenCredential = GoogleIdTokenCredential
            .createFrom(credential.data)
        val googleIdToken = googleIdTokenCredential.idToken

        return GoogleAuthProvider.getCredential(googleIdToken, null)
    }
}