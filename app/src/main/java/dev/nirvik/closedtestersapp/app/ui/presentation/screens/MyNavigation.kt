package dev.nirvik.closedtestersapp.app.ui.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.nirvik.closedtestersapp.app.ui.presentation.auth.SignInScreen
import dev.nirvik.closedtestersapp.app.ui.presentation.auth.SignUpScreen

@Composable
fun MyNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SignIn.route) {
        composable(Screen.SignIn.route) { SignInScreen(
            onSignInClick = { /* Handle sign-in logic, navigate to Home */  navController.navigate(Screen.Home.route)},
            onSignUpClick = { navController.navigate(Screen.SignUp.route) }
        ) }
        composable(Screen.SignUp.route) { SignUpScreen(
            onSignInClick = { navController.navigate(Screen.SignIn.route) }
        )
        }
        composable(Screen.Home.route) { HomeScreen() }
        // ... other composable routes
    }
}