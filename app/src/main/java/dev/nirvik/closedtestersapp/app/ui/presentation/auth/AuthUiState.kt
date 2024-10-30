package dev.nirvik.closedtestersapp.app.ui.presentation.auth

import dev.nirvik.closedtestersapp.domain.model.User

data class AuthUiState(
    val email: String="",
    val password: String="",
    var isLoading: Boolean = false,
    val user: User? = null,
    val error: String? = null,
    val isSignedIn: Boolean = false,
)