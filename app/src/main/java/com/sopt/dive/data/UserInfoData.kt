package com.sopt.dive.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sopt.dive.UserInfo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UserRepository(private val dataStore: DataStore<Preferences>) {
    private object PreferencesKeys {
        val CURRENT_USER_INFO = stringPreferencesKey("current_user_info")
    }

    suspend fun getCurrentUserInfo(): UserInfo {
        return dataStore.data.map { preferences ->
            val jsonString = preferences[PreferencesKeys.CURRENT_USER_INFO]

            if (jsonString != null) {
                Json.decodeFromString<UserInfo>(jsonString)
            }
            UserInfo()
        }.first()
    }
    suspend fun setCurrentUserInfo(userInfo: UserInfo) {
        val jsonString = Json.encodeToString<UserInfo>(userInfo)
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.CURRENT_USER_INFO] = jsonString
        }
    }
}