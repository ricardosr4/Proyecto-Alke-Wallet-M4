package com.example.proyectoalkewallet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoalkewallet.di.AppContainer
import com.example.proyectoalkewallet.domain.model.User
import com.example.proyectoalkewallet.domain.usecase.GetCurrentUserUseCase
import com.example.proyectoalkewallet.domain.usecase.LogoutUseCase
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val getCurrentUserUseCase = GetCurrentUserUseCase(AppContainer.authRepository)
    private val logoutUseCase = LogoutUseCase(AppContainer.authRepository)

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    private val _logoutComplete = MutableLiveData(false)
    val logoutComplete: LiveData<Boolean> = _logoutComplete

    fun loadUser() {
        viewModelScope.launch {
            _user.value = getCurrentUserUseCase()
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
            _logoutComplete.value = true
        }
    }
}
