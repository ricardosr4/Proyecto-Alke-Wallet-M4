package com.example.proyectoalkewallet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoalkewallet.di.AppContainer
import com.example.proyectoalkewallet.domain.usecase.GetCurrentUserUseCase
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val getCurrentUserUseCase = GetCurrentUserUseCase(AppContainer.authRepository)

    private val _hasActiveSession = MutableLiveData<Boolean>()
    val hasActiveSession: LiveData<Boolean> = _hasActiveSession

    fun checkSession() {
        viewModelScope.launch {
            _hasActiveSession.value = getCurrentUserUseCase() != null
        }
    }
}
