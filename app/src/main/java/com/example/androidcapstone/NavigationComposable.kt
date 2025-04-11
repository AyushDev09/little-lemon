package com.example.androidcapstone

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MyNavigation(navController: NavHostController, database: AppDatabase) {
    val startDestination = if (isUserOnboarded()) Home.route else Onboarding.route

    // The navigation graph (NavHost)
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Onboarding.route) {
            OnboardingScreen(navController)
        }
        composable(Home.route) {
            HomeScreen(navController, database)
        }
        composable(Profile.route) {
            ProfileScreen(navController)
        }
    }
}

fun isUserOnboarded(): Boolean {
    return false // Return true if user is onboarded, false if not
}
