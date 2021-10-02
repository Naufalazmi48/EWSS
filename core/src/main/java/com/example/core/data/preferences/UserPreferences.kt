package com.example.core.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.core.presentation.model.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(private val dataStore: DataStore<Preferences>) {
    companion object {
        private val API_KEY = stringPreferencesKey("api_key")
        private val USER_AVATAR = stringPreferencesKey("user_avatar")
        private val USER_NAME = stringPreferencesKey("user_name")
        private val USER_PHONE = stringPreferencesKey("user_phone")
        private val USER_EMAIL = stringPreferencesKey("user_email")
    }

    fun getApiKey(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[API_KEY]
        }
    }

    fun getUser(): Flow<Account> {
        return dataStore.data.map { preferences ->
            Account(
                avatar = preferences[USER_AVATAR],
                name = preferences[USER_NAME],
                phone = preferences[USER_PHONE],
                email = preferences[USER_EMAIL]
            )
        }
    }

    suspend fun saveApiKey(apiKey: String) {
        dataStore.edit { preferences ->
            preferences[API_KEY] = apiKey
        }
    }

    suspend fun saveUser(avatar:String? = null, name:String?, phone:String?, email:String?) {
        dataStore.edit { preferences ->
            preferences[USER_AVATAR] = avatar ?: ""
            preferences[USER_NAME] = name ?: ""
            preferences[USER_PHONE] = phone ?: ""
            preferences[USER_EMAIL] = email ?: ""
        }
    }

    suspend fun clearAllData() {
        dataStore.edit { it.clear() }
    }
}