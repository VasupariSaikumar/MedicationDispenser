package com.smartdispenser.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartdispenser.data.repository.PreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    data class Error(val message: String) : AuthState()
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    fun login(email: String, pass: String) {
        if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _authState.value = AuthState.Error("Please enter a valid email address.")
            return
        }
        if (pass.length < 6) {
            _authState.value = AuthState.Error("Password must be at least 6 characters.")
            return
        }

        viewModelScope.launch {
            _authState.value = AuthState.Loading
            delay(1200) // Simulate network/auth authentication delay
            preferencesRepository.saveUserSession(
                name = email.substringBefore("@").capitalize(),
                email = email,
                role = "PATIENT"
            )
            _authState.value = AuthState.Success
        }
    }

    fun register(
        name: String,
        email: String,
        pass: String,
        confirmPass: String,
        phone: String,
        role: String
    ) {
        if (name.isBlank()) {
            _authState.value = AuthState.Error("Please enter your full name.")
            return
        }
        if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _authState.value = AuthState.Error("Please enter a valid email address.")
            return
        }
        if (pass.length < 6) {
            _authState.value = AuthState.Error("Password must be at least 6 characters.")
            return
        }
        if (pass != confirmPass) {
            _authState.value = AuthState.Error("Passwords do not match.")
            return
        }
        if (phone.length < 10) {
            _authState.value = AuthState.Error("Please enter a valid phone number.")
            return
        }

        viewModelScope.launch {
            _authState.value = AuthState.Loading
            delay(1500)
            preferencesRepository.saveUserSession(
                name = name,
                email = email,
                role = role
            )
            _authState.value = AuthState.Success
        }
    }

    fun resetAuthState() {
        _authState.value = AuthState.Idle
    }
}
