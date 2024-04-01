package com.example.noteapp.feature_login.data.repository

import com.example.noteapp.feature_login.domain.model.NotVerifiedEmailException
import com.example.noteapp.feature_login.domain.model.User
import com.example.noteapp.feature_login.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
) : UserRepository {
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
}