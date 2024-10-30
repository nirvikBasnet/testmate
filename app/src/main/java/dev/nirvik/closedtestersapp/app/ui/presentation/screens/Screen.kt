package dev.nirvik.closedtestersapp.app.ui.presentation.screens

sealed class Screen(val route: String) {
    object SignIn : Screen("signin")
    object SignUp : Screen("signup")
    object Home : Screen("home")
    // ... other screens
}