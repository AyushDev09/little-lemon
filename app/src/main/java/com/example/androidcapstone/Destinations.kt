package com.example.androidcapstone

// Define the Destinations interface
interface Destinations {
    val route: String
}

// Define the screen routes by creating objects for each screen
object Onboarding : Destinations {
    override val route = "OnboardingScreen"
}

object Home : Destinations {
    override val route = "HomeScreen"
}

object Profile : Destinations {
    override val route = "ProfileScreen"
}