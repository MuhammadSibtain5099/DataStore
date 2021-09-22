package com.sibtain.datastorefinal

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map


class UserDetails(val context: Context) {
    private val USER_PREFERENCES_NAME = "user_preferences"

    private val Context.dataStore by preferencesDataStore(
        name = USER_PREFERENCES_NAME
    )

    companion object {
        val USERNAME = stringPreferencesKey("USER_NAME")
        val AGE = intPreferencesKey("AGE")
    }

    suspend fun storeDetails(userName: String, age: Int) {
        context.dataStore.edit {
            it[USERNAME] = userName
            it[AGE] = age
        }
    }

     fun getName() = context.dataStore.data.map {
        it[USERNAME] ?: "No Name "

    }

    suspend fun getAge() = context.dataStore.data.map {
        it[AGE] ?: -1
    }


}