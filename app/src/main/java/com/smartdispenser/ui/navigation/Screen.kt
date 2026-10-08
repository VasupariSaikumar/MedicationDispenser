package com.smartdispenser.ui.navigation

/**
 * Sealed class representing all app navigation routes.
 */
sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Medications : Screen("medications")
    object AddEditMedication : Screen("add_edit_medication?medicationId={medicationId}") {
        fun createRoute(medicationId: String? = null): String {
            return if (medicationId != null) "add_edit_medication?medicationId=$medicationId" else "add_edit_medication"
        }
    }
    object Dispenser : Screen("dispenser")
    object Adherence : Screen("adherence")
    object Profile : Screen("profile")
    object Caregiver : Screen("caregiver")
}
