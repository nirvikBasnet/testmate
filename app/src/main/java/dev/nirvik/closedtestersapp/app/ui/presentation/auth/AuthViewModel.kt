package dev.nirvik.closedtestersapp.app.ui.presentation.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import dev.nirvik.closedtestersapp.data.repository.AuthRepositoryImpl
import dev.nirvik.closedtestersapp.domain.model.User
import dev.nirvik.closedtestersapp.domain.repositories.AuthRepository
import dev.nirvik.closedtestersapp.domain.usecases.SignInUseCase
import dev.nirvik.closedtestersapp.domain.usecases.SignUpUseCase
import kotlinx.coroutines.launch

class AuthViewModel() : ViewModel(){

    private val authRepository : AuthRepository = AuthRepositoryImpl(FirebaseAuth.getInstance())
    private val signInUseCase = SignInUseCase(authRepository)
    private val signUpUseCase = SignUpUseCase(authRepository)


    var uiState by mutableStateOf(AuthUiState())
        private set

    fun onSignIn(){
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            val result = signInUseCase(uiState.email,uiState.password)
            uiState = if(result.isSuccess){
                uiState.copy(
                    isLoading = false,
                    user = result.getOrNull(),
                    isSignedIn = true,
                    error = result.exceptionOrNull()?.message
                )
            }else{
                uiState.copy(
                    isLoading = false,
                    error = result.exceptionOrNull()?.message
                )
            }


        }
    }

    fun onSignUp() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            val result = signUpUseCase(uiState.email, uiState.password)
            uiState = if(result.isSuccess){
                uiState.copy(
                    isLoading = false,
                    user = result.getOrNull(),
                    isSignedIn = true,
                    error = result.exceptionOrNull()?.message
                )
            }else{
                uiState.copy(
                    isLoading = false,
                    error = result.exceptionOrNull()?.message
                )
            }
        }
    }

    fun onEmailChange(newEmail: String) {
            uiState = uiState.copy(
                email = newEmail
            )
    }

    fun onPasswordChange(newPassword: String) {
        uiState = uiState.copy(
            password = newPassword
        )
    }
}