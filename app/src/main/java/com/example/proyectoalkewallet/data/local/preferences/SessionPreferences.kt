package com.example.proyectoalkewallet.data.local.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.sessionDataStore by preferencesDataStore(name = "session")

class SessionPreferences(
    private val context: Context,
) {

    suspend fun saveSession(userId: Int) {
        context.sessionDataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = true
            preferences[CURRENT_USER_ID] = userId
        }
    }

    suspend fun getCurrentUserId(): Int? {
        val preferences = context.sessionDataStore.data.first()
        val isLoggedIn = preferences[IS_LOGGED_IN] ?: false
        return preferences[CURRENT_USER_ID].takeIf { isLoggedIn }
    }

    suspend fun clearSession() {
        context.sessionDataStore.edit { preferences ->
            preferences.clear()
        }
    }

    private companion object {
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val CURRENT_USER_ID = intPreferencesKey("current_user_id")
    }
}
