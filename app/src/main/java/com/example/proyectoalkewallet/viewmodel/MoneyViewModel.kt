package com.example.proyectoalkewallet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoalkewallet.di.AppContainer
import com.example.proyectoalkewallet.domain.usecase.DepositMoneyUseCase
import com.example.proyectoalkewallet.domain.usecase.WithdrawMoneyUseCase
import kotlinx.coroutines.launch

class MoneyViewModel : ViewModel() {

    private val depositMoneyUseCase = DepositMoneyUseCase(AppContainer.accountRepository)
    private val withdrawMoneyUseCase = WithdrawMoneyUseCase(AppContainer.accountRepository)

    private val _state = MutableLiveData(MoneyUiState())
    val state: LiveData<MoneyUiState> = _state

    fun deposit(amountText: String?) {
        _state.value = MoneyUiState(isLoading = true)
        viewModelScope.launch {
            _state.value = MoneyUiState(success = depositMoneyUseCase(amountText))
        }
    }

    fun withdraw(amountText: String?) {
        _state.value = MoneyUiState(isLoading = true)
        viewModelScope.launch {
            _state.value = MoneyUiState(success = withdrawMoneyUseCase(amountText))
        }
    }

    fun clearResult() {
        _state.value = MoneyUiState()
    }
}

data class MoneyUiState(
    val isLoading: Boolean = false,
    val success: Boolean? = null,
)
