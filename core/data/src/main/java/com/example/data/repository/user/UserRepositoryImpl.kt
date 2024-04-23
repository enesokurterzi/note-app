package com.example.data.repository.user

import com.example.model.NotVerifiedEmailException
import com.example.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
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

    override fun getUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }
}