package com.example.proyectoalkewallet.data.repository

import com.example.proyectoalkewallet.data.local.database.UserDao
import com.example.proyectoalkewallet.data.local.preferences.SessionPreferences
import com.example.proyectoalkewallet.data.mapper.toDomain
import com.example.proyectoalkewallet.data.mapper.toEntity
import com.example.proyectoalkewallet.data.remote.AlkeWalletApi
import com.example.proyectoalkewallet.data.remote.dto.LoginRequestDto
import com.example.proyectoalkewallet.data.remote.dto.RegisterRequestDto
import com.example.proyectoalkewallet.domain.model.User
import com.example.proyectoalkewallet.domain.repository.AuthRepository
import com.example.proyectoalkewallet.domain.repository.UserNotFoundException

class AuthRepositoryImpl(
    private val api: AlkeWalletApi,
    private val userDao: UserDao,
    private val sessionPreferences: SessionPreferences,
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<User> = runCatching {
        val loginResponse = api.login(LoginRequestDto(email, password))
        check(loginResponse.isSuccessful && loginResponse.body() != null)

        val usersResponse = api.getUsers()
        check(usersResponse.isSuccessful)

        val users = usersResponse.body().orEmpty().map { it.toDomain() }
        val selectedUser = users.firstOrNull { user ->
            user.email.equals(email, ignoreCase = true)
        } ?: throw UserNotFoundException()

        val savedUsers = userDao.getAllUsers().associateBy { user -> user.id }
        val savedUser = savedUsers[selectedUser.id]?.toDomain()
        val usersToCache = users.map { user ->
            val savedBalance = savedUsers[user.id]?.balance
            user.copy(balance = savedBalance ?: user.balance)
        }
        userDao.upsertUsers(usersToCache.map { it.toEntity() })

        val userDetail = loadUserDetail(selectedUser)
        val user = userDetail.copy(balance = savedUser?.balance ?: userDetail.balance)
        saveSession(user)
    }

    override suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
    ): Result<User> = runCatching {
        val request = RegisterRequestDto(firstName, lastName, email, password)
        val response = api.register(request)
        val authResponse = response.body()
        check(response.isSuccessful && authResponse != null)

        val user = authResponse.user.toDomain().copy(
            id = userDao.getNextLocalId(),
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
        )
        saveSession(user)
    }

    override suspend fun getCurrentUser(): User? {
        val userId = sessionPreferences.getCurrentUserId() ?: return null
        return userDao.getUserById(userId)?.toDomain()
    }

    override suspend fun logout() {
        sessionPreferences.clearSession()
    }

    private suspend fun loadUserDetail(selectedUser: User): User {
        val response = runCatching { api.getUser(selectedUser.id) }.getOrNull()
        val detail = response?.body()?.toDomain()

        return detail?.takeIf { user ->
            response.isSuccessful &&
                user.id == selectedUser.id &&
                user.email.equals(selectedUser.email, ignoreCase = true)
        } ?: selectedUser
    }

    private suspend fun saveSession(user: User): User {
        userDao.upsertUser(user.toEntity())
        sessionPreferences.saveSession(user.id)
        return user
    }
}
