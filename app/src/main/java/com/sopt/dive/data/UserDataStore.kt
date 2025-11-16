package com.sopt.dive.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sopt.dive.UserInfo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UserDataStore(private val dataStore: DataStore<Preferences>) {
    private object PreferencesKeys {
        val CURRENT_USER_UUID = stringPreferencesKey("current_user_uuid")
    }

    suspend fun getCurrentUserUUID(): Int {
        return dataStore.data.map { preferences ->
            preferences[PreferencesKeys.CURRENT_USER_UUID]?.toInt() ?: 0
        }.first()
    }

    suspend fun setCurrentUserUUID(id: Int): Int {
        dataStore.edit {
                preferences -> preferences[PreferencesKeys.CURRENT_USER_UUID] = id.toString()
        }
        return id
    }
}