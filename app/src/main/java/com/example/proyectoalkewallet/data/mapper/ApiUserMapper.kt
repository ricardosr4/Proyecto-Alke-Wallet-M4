package com.example.proyectoalkewallet.data.mapper

import com.example.proyectoalkewallet.data.remote.dto.ApiUserDto
import com.example.proyectoalkewallet.domain.model.User

fun ApiUserDto.toDomain(): User = User(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    balance = balance,
)
