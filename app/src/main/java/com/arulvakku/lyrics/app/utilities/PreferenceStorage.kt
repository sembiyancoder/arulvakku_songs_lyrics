package com.arulvakku.lyrics.app.utilities

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


interface PreferenceStorage {

    val songCategory: Flow<String>
    val song: Flow<String>
    suspend fun setSongCategory(songCategory: String)
    suspend fun setSong(song: String)
    suspend fun clearPreferenceStorage()

}

@Singleton
class AppPrefsStorage @Inject constructor(
    @ApplicationContext context: Context
) : PreferenceStorage {

    private val dataStore: DataStore<Preferences> =
        context.createDataStore(name = "Arulvakku-Song-Pref-Storage")

    private object PreferencesKeys {
        val SONG_CATEGORY = preferencesKey<String>("song_category")
        val SONG = preferencesKey<String>("songs")
    }

    override val songCategory: Flow<String>
        get() = dataStore.getValueAsFlow(PreferencesKeys.SONG_CATEGORY, "")

    override val song: Flow<String>
        get() = dataStore.getValueAsFlow(PreferencesKeys.SONG, "")

    override suspend fun setSongCategory(songCategory: String) {
        dataStore.setValue(PreferencesKeys.SONG_CATEGORY, songCategory)
    }

    override suspend fun setSong(song: String) {
        dataStore.setValue(PreferencesKeys.SONG, song)
    }

    override suspend fun clearPreferenceStorage() {
        dataStore.edit {
            it.clear()
        }
    }

    /***
     * handy function to save key-value pairs in Preference. Sets or updates the value in Preference
     * @param key used to identify the preference
     * @param value the value to be saved in the preference
     */
    private suspend fun <T> DataStore<Preferences>.setValue(
        key: Preferences.Key<T>,
        value: T
    ) {
        this.edit { preferences ->
            // save the value in prefs
            preferences[key] = value
        }
    }

    /***
     * handy function to return Preference value based on the Preference key
     * @param key  used to identify the preference
     * @param defaultValue value in case the Preference does not exists
     * @throws Exception if there is some error in getting the value
     * @return [Flow] of [T]
     */
    private fun <T> DataStore<Preferences>.getValueAsFlow(
        key: Preferences.Key<T>,
        defaultValue: T
    ): Flow<T> {
        return this.data.catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                // we try again to store the value in the map operator
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            // return the default value if it doesn't exist in the storage
            preferences[key] ?: defaultValue
        }
    }


}