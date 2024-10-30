package dev.nirvik.closedtestersapp.data.repository

import com.google.firebase.auth.FirebaseAuth
import dev.nirvik.closedtestersapp.domain.model.User
import dev.nirvik.closedtestersapp.domain.repositories.AuthRepository
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val auth: FirebaseAuth
) : AuthRepository{
    override suspend fun signIn(email: String, password: String): Result<User> {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email,password).await()
            val user = authResult.user?.let {
                User(it.uid, it.displayName?:"",it.email?:"")
            }
            if(user != null) Result.success(user) else Result.failure(Exception("User Not Found"))
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun signUp(email: String, password: String): Result<User> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email,password).await()
            val user = authResult.user?.let {
                User(it.uid, it.displayName?:"",it.email?:"")
            }
            if(user!=null) Result.success(user) else Result.failure(Exception("Please Try Again"))
        }catch (e: Exception){
            Result.failure(e)
        }
    }

}