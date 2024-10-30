package dev.nirvik.closedtestersapp.domain.repositories

import dev.nirvik.closedtestersapp.domain.model.User

interface AuthRepository {
    suspend fun signIn(email: String, password: String) : Result<User>
    suspend fun signUp(email: String, password: String) : Result<User>
}