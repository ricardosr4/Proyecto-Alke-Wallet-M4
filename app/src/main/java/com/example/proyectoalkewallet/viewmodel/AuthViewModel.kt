package com.example.proyectoalkewallet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoalkewallet.di.AppContainer
import com.example.proyectoalkewallet.domain.model.User
import com.example.proyectoalkewallet.domain.repository.UserNotFoundException
import com.example.proyectoalkewallet.domain.usecase.LoginUseCase
import com.example.proyectoalkewallet.domain.usecase.RegisterUseCase
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val loginUseCase = LoginUseCase(AppContainer.authRepository)
    private val registerUseCase = RegisterUseCase(AppContainer.authRepository)

    private val _state = MutableLiveData(AuthUiState())
    val state: LiveData<AuthUiState> = _state

    fun login(email: String, password: String) {
        _state.value = AuthUiState(isLoading = true)
        viewModelScope.launch {
            val result = loginUseCase(email, password)
            result.fold(
                onSuccess = { user ->
                    _state.value = AuthUiState(user = user)
                },
                onFailure = { error ->
                    val authError = if (error is UserNotFoundException) {
                        AuthError.USER_NOT_FOUND
                    } else {
                        AuthError.LOGIN_FAILED
                    }
                    _state.value = AuthUiState(error = authError)
                },
            )
        }
    }

    fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
    ) {
        _state.value = AuthUiState(isLoading = true)
        viewModelScope.launch {
            val result = registerUseCase(firstName, lastName, email, password)
            result.fold(
                onSuccess = { user ->
                    _state.value = AuthUiState(user = user)
                },
                onFailure = {
                    _state.value = AuthUiState(error = AuthError.REGISTER_FAILED)
                },
            )
        }
    }

    fun clearResult() {
        _state.value = AuthUiState()
    }
}

data class AuthUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: AuthError? = null,
)

enum class AuthError {
    USER_NOT_FOUND,
    LOGIN_FAILED,
    REGISTER_FAILED,
}
