package com.example.proyectoalkewallet.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertUsers(users: List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    suspend fun getUserById(userId: Int): UserEntity?

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserEntity>

    @Query("UPDATE users SET balance = :balance WHERE id = :userId")
    suspend fun updateBalance(userId: Int, balance: Int)

    @Query("SELECT COALESCE(MIN(id), 0) - 1 FROM users WHERE id <= 0")
    suspend fun getNextLocalId(): Int
}
