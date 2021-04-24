package com.emrehmrc.pref_datastore.repo

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.emrehmrc.pref_datastore.helper.myDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

/**
 *  Rev           1.0
 *  Author        EmreHamurcu
 *  Date          4/24/2021
 *  FileName      DataStoreRepository
 */
class DataStoreRepository(private val context: Context) {

    fun readStringFromDataStore(key: Preferences.Key<String>): Flow<String> {
        return context.myDataStore.data
            .catch { ex ->
                if (ex is IOException) {
                    emit(emptyPreferences())
                } else throw ex
            }
            .map { preferences ->
                val showCompleted = preferences[key] ?: "null"
                showCompleted
            }
    }

    fun readBooleanFromDataStore(key: Preferences.Key<Boolean>): Flow<Boolean> {
        return context.myDataStore.data
            .catch { ex ->
                if (ex is IOException) {
                    emit(emptyPreferences())
                } else throw ex
            }
            .map { preferences ->
                val isChecked = preferences[key] ?: false
                isChecked
            }
    }

    fun readIntegerFromDataStore(key: Preferences.Key<Int>): Flow<Int> {
        return context.myDataStore.data
            .catch { ex ->
                if (ex is IOException) {
                    emit(emptyPreferences())
                } else throw ex
            }
            .map { preferences ->
                val isChecked = preferences[key] ?: -1
                isChecked
            }
    }

    fun readLongFromDataStore(key: Preferences.Key<Long>): Flow<Long> {
        return context.myDataStore.data
            .catch { ex ->
                if (ex is IOException) {
                    emit(emptyPreferences())
                } else throw ex
            }
            .map { preferences ->
                val isChecked = preferences[key] ?: 0
                isChecked
            }
    }

    fun readFloatFromDataStore(key: Preferences.Key<Float>): Flow<Float> {
        return context.myDataStore.data
            .catch { ex ->
                if (ex is IOException) {
                    emit(emptyPreferences())
                } else throw ex
            }
            .map { preferences ->
                val isChecked = preferences[key] ?: 0f
                isChecked
            }
    }

    suspend fun saveStringToDataStore(key: Preferences.Key<String>, name: String) {
        context.myDataStore.edit { preferences ->
            preferences[key] = name
        }
    }

    suspend fun saveBooleanToDataStore(key: Preferences.Key<Boolean>, isChecked: Boolean) {
        context.myDataStore.edit { preferences ->
            preferences[key] = isChecked
        }
    }

    suspend fun saveIntToDataStore(key: Preferences.Key<Int>, int: Int) {
        context.myDataStore.edit { preferences ->
            preferences[key] = int
        }
    }

    suspend fun saveLongToDataStore(key: Preferences.Key<Long>, long: Long) {
        context.myDataStore.edit { preferences ->
            preferences[key] = long
        }
    }

    suspend fun saveFloatToDataStore(key: Preferences.Key<Float>, float: Float) {
        context.myDataStore.edit { preferences ->
            preferences[key] = float
        }
    }
}