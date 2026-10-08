package com.smartdispenser.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.smartdispenser.data.repository.PreferencesRepository
import com.smartdispenser.ui.screens.auth.AuthViewModel
import com.smartdispenser.ui.screens.auth.LoginScreen
import com.smartdispenser.ui.screens.auth.RegisterScreen
import com.smartdispenser.ui.screens.home.HomeScreen
import com.smartdispenser.ui.screens.onboarding.OnboardingScreen
import com.smartdispenser.ui.screens.placeholder.GenericPlaceholderScreen
import com.smartdispenser.ui.screens.splash.SplashScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    preferencesRepository: PreferencesRepository
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        // Splash Screen
        composable(Screen.Splash.route) {
            SplashScreen(
                preferencesRepository = preferencesRepository,
                onNavigateToOnboarding = {
                    navController.navigate(Screen.Onboarding.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        // Onboarding Screen
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onFinishOnboarding = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        // Login Screen
        composable(Screen.Login.route) {
            val authViewModel: AuthViewModel = hiltViewModel()
            LoginScreen(
                viewModel = authViewModel,
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        // Register Screen
        composable(Screen.Register.route) {
            val authViewModel: AuthViewModel = hiltViewModel()
            RegisterScreen(
                viewModel = authViewModel,
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                }
            )
        }

        // Home Screen
        composable(Screen.Home.route) {
            HomeScreen(
                preferencesRepository = preferencesRepository,
                onNavigateToAddMedication = {
                    navController.navigate(Screen.AddEditMedication.createRoute())
                },
                onNavigateToDispenser = {
                    navController.navigate(Screen.Dispenser.route)
                },
                onNavigateToProfile = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }

        // Medications Screen
        composable(Screen.Medications.route) {
            GenericPlaceholderScreen(
                title = "Medications List",
                onBack = { navController.popBackStack() }
            )
        }

        // Add/Edit Medication Screen
        composable(Screen.AddEditMedication.route) {
            GenericPlaceholderScreen(
                title = "Add / Edit Medication",
                onBack = { navController.popBackStack() }
            )
        }

        // BLE Dispenser Screen
        composable(Screen.Dispenser.route) {
            GenericPlaceholderScreen(
                title = "BLE Dispenser Pairing",
                onBack = { navController.popBackStack() }
            )
        }

        // Adherence Screen
        composable(Screen.Adherence.route) {
            GenericPlaceholderScreen(
                title = "Adherence Analytics",
                onBack = { navController.popBackStack() }
            )
        }

        // Profile Screen
        composable(Screen.Profile.route) {
            GenericPlaceholderScreen(
                title = "Profile & Settings",
                onBack = { navController.popBackStack() }
            )
        }

        // Caregiver Screen
        composable(Screen.Caregiver.route) {
            GenericPlaceholderScreen(
                title = "Caregiver Dashboard",
                onBack = { navController.popBackStack() }
            )
        }
    }
}
