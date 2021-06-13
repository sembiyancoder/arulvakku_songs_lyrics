package com.arulvakku.lyrics.app.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "arulvakku_song_pref_storage")

@Singleton
class PreferenceImpl @Inject constructor(@ApplicationContext context: Context) : PreferenceStorage {

    private val dataStore = context.dataStore

    //preference keys
    private object PreferencesKeys {
        val UI_MODE_KEY = booleanPreferencesKey("ui_mode")
        val GLOBAL_USER_INFO = booleanPreferencesKey("user_global_info")
        val GLOBAL_USER_MESSAGE = stringPreferencesKey("user_global_message")
    }

    //get saved key
    override fun savedKey() = dataStore.data.catch { it ->
        if (it is IOException) {
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map {
        it[PreferencesKeys.UI_MODE_KEY] ?: false
    }

    //set saved key
    override suspend fun setSavedKey(order: Boolean) {
        dataStore.edit {
            it[PreferencesKeys.UI_MODE_KEY] = order
        }
    }

    override fun isUserClosedGlobalInfoKey() = dataStore.data.catch { it ->
        if (it is IOException) {
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map {
        it[PreferencesKeys.GLOBAL_USER_INFO] ?: false
    }


    override suspend fun setUserClosedGlobalInfoKey(order: Boolean) {
        dataStore.edit {
            it[PreferencesKeys.GLOBAL_USER_INFO] = order
        }
    }

    override fun getGlobalMessage() = dataStore.data.catch { it ->
        if (it is IOException) {
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map {
        it[PreferencesKeys.GLOBAL_USER_MESSAGE] ?: "user_global_message"
    }

    override suspend fun setGlobalMessage(message: String) {
        dataStore.edit {
            it[PreferencesKeys.GLOBAL_USER_MESSAGE] = message
        }
    }
}