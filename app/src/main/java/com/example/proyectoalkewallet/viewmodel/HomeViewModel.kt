package com.example.proyectoalkewallet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoalkewallet.di.AppContainer
import com.example.proyectoalkewallet.domain.model.User
import com.example.proyectoalkewallet.domain.usecase.GetBalanceUseCase
import com.example.proyectoalkewallet.domain.usecase.GetCurrentUserUseCase
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val getCurrentUserUseCase = GetCurrentUserUseCase(AppContainer.authRepository)
    private val getBalanceUseCase = GetBalanceUseCase(AppContainer.accountRepository)

    private val _state = MutableLiveData<HomeUiState>()
    val state: LiveData<HomeUiState> = _state

    fun loadData() {
        viewModelScope.launch {
            _state.value = HomeUiState(
                user = getCurrentUserUseCase(),
                balance = getBalanceUseCase(),
            )
        }
    }
}

data class HomeUiState(
    val user: User?,
    val balance: Int,
)
