package com.arulvakku.lyrics.app.utilities

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.*
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
    @ApplicationContext private val context: Context
) : PreferenceStorage {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Arulvakku-Song-Pref-Storage")


    companion object {
        val SONG_CATEGORY = stringPreferencesKey("song_category")
        val SONG = stringPreferencesKey("songs")
    }


    override val songCategory: Flow<String> = context.dataStore.data.map {
        val songCategory = it[SONG_CATEGORY] ?: ""
        songCategory
    }

   /* override val songCategory: Flow<String>
        get() = dataStore.getValueAsFlow(PreferencesKeys.SONG_CATEGORY, "")
*/

    override val song: Flow<String> = context.dataStore.data.map {
        val song = it[SONG] ?: ""
        song
    }

  /*  override val song: Flow<String>
        get() = dataStore.getValueAsFlow(PreferencesKeys.SONG, "")*/

  /*  override suspend fun setSongCategory(songCategory: String) {
        dataStore.setValue(PreferencesKeys.SONG_CATEGORY, songCategory)
    }*/


     override suspend fun setSongCategory(songCategory: String) {
        context.dataStore.edit {
            it[SONG_CATEGORY] = songCategory
        }
    }

    override suspend fun setSong(song: String){
        context.dataStore.edit {
            it[SONG] = song
        }
    }
   /* override suspend fun setSong(song: String) {
        dataStore.setValue(PreferencesKeys.SONG, song)
    }*/

    override suspend fun clearPreferenceStorage() {
        context.dataStore.edit {
            it.clear()
        }
    }

}