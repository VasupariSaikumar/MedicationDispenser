# 💊 MediDispense - Smart Medication & Adherence Assistant

**MediDispense** (SmartMedication) is a modern, high-reliability Android application designed to automate pill dispensing, track medication adherence, and connect caregivers with patients in real time. Powered by an **ESP32 microcontroller** over **Bluetooth Low Energy (BLE)**, MediDispense ensures timely dose releases and provides intelligent notifications to avoid missed medications.

---

## 🌟 Key Features

- ⏱️ **Automated Smart Dispensing:** Seamless BLE pairing with an ESP32 hardware dispenser for scheduled, precise pill release.
- 🔐 **Authentication & Role Support:** Firebase Auth integration with dedicated profiles for **Patients** and **Caregivers**.
- 📊 **Adherence Analytics:** Real-time dose logging with hardware sensor feedback, weekly adherence trends, and exportable logs.
- 🚨 **Caregiver Alert Network:** Automated notifications and alerts sent to caregivers when doses are missed or delayed.
- 📱 **Modern Jetpack Compose UI:** Material 3 design system with dynamic themes, smooth animations, and interactive onboarding.
- 💾 **Offline-First Persistence:** Jetpack DataStore for local preference management and Room Database for reliable offline data caching.

---

## 🛠️ Architecture & Tech Stack

MediDispense follows modern Android development best practices, using **Clean Architecture** with **MVVM** and **Unidirectional Data Flow (UDF)**.

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose with Material 3 Design
- **Dependency Injection:** Hilt (Dagger)
- **Local Data Persistence:**
  - **Jetpack DataStore (Preferences):** User sessions, onboarding states, user roles.
  - **Room Database:** Local medication database and adherence history.
- **Backend Services:**
  - **Firebase Authentication:** Email/password authentication.
  - **Firebase Firestore:** Cloud synchronization for caregiver dashboards and medication schedules.
- **Connectivity:** Bluetooth Low Energy (BLE) for ESP32 hardware pairing and control.
- **Navigation:** Jetpack Compose Navigation with type-safe route definitions.

---

## 📱 App Navigation Flow

```
Splash Screen ──► Onboarding Screen ──► Login / Register
                                              │
                                              ▼
                                       Home Dashboard
                        ┌─────────────────────┼─────────────────────┐
                        ▼                     ▼                     ▼
               BLE Dispenser Pairing   Add/Edit Medication    Profile & Settings
                        │                     │
                        ▼                     ▼
                Adherence Analytics   Caregiver Dashboard
```

---

## 📂 Project Structure

```
SmartMedication/
├── app/
│   ├── build.gradle.kts           # Module-level build configuration & dependencies
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           └── java/com/smartdispenser/
│               ├── MainActivity.kt # Single-activity entry point
│               ├── SmartMedicationApp.kt
│               ├── data/          # Data repositories & DataStore management
│               ├── di/            # Dependency injection modules (Hilt)
│               └── ui/
│                   ├── navigation/# Screen routes & NavGraph
│                   ├── screens/   # Compose screens (Splash, Onboarding, Auth, Home, etc.)
│                   └── theme/     # Material 3 colors, typography, & theme setup
└── build.gradle.kts               # Project-level build configuration
```

---

## 🚀 Getting Started

### Prerequisites
- **Android Studio:** Ladybug (2024.2.1) or newer
- **JDK:** Java 17
- **Min SDK:** 26 (Android 8.0 Oreo)
- **Target SDK:** 35 (Android 15)

### Build & Run
1. **Clone the repository:**
   ```bash
   git clone https://github.com/VasupariSaikumar/MedicationDispenser.git
   cd MedicationDispenser
   ```
2. **Open in Android Studio** and wait for Gradle sync to complete.
3. **Connect an Android device / Emulator** running Android 8.0 (API level 26) or higher.
4. **Build and Run:**
   ```bash
   ./gradlew assembleDebug
   ```

---

## 🔌 Hardware Integration (ESP32)

The application pairs with an **ESP32-based Smart Pill Dispenser** over Bluetooth Low Energy (BLE):
- **Service & Characteristic Discovery:** Connects to the ESP32 BLE GATT server.
- **Automated Dispense Triggers:** Transmits command payloads to trigger motor/servo releases at designated dosage times.
- **Sensor Verification:** Receives acknowledgment signals from hardware IR/limit sensors to log taken or missed doses accurately.

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).
