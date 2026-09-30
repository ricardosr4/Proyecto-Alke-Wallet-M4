package com.example.proyectoalkewallet.data.mapper

import com.example.proyectoalkewallet.data.local.database.UserEntity
import com.example.proyectoalkewallet.domain.model.User

fun User.toEntity(): UserEntity = UserEntity(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    balance = balance,
)

fun UserEntity.toDomain(): User = User(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    balance = balance,
)
