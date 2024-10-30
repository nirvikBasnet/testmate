package dev.nirvik.closedtestersapp.domain.usecases

import dev.nirvik.closedtestersapp.domain.model.User
import dev.nirvik.closedtestersapp.domain.repositories.AuthRepository

class SignInUseCase (private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<User>{
        return repository.signIn(email,password)
    }
}