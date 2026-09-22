package com.example.proyectoalkewallet.model

object Account {

    private var balance = 124000

    fun getBalance(): Int = balance

    fun deposit(amount: Double): Boolean {
        if (amount <= 0) {
            return false
        }

        balance += amount.toInt()
        return true
    }

    fun withdraw(amount: Double): Boolean {
        if (amount <= 0 || amount > balance) {
            return false
        }

        balance -= amount.toInt()
        return true
    }
}
