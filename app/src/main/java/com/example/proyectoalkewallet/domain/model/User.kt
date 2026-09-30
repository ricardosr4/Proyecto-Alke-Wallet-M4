package com.example.proyectoalkewallet.domain.model

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val balance: Int,
)
