package com.smartdispenser

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Main Application class for MediDispense.
 * Initializes Hilt Dependency Injection container.
 */
@HiltAndroidApp
class SmartMedicationApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
