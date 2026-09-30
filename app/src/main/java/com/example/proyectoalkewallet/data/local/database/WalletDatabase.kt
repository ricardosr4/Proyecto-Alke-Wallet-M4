package com.example.proyectoalkewallet.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class WalletDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var instance: WalletDatabase? = null

        fun getInstance(context: Context): WalletDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    WalletDatabase::class.java,
                    "alke_wallet.db",
                ).build().also { database ->
                    instance = database
                }
            }
    }
}
