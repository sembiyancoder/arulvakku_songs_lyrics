package com.arulvakku.lyrics.app.preferences


import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {

    //check if user has saved some details to database, move to details screen if saved
    //set allSongs sort order
    fun savedKey(): Flow<Boolean>
    suspend fun setSavedKey(order: Boolean)

    //to show the info
    fun isUserClosedGlobalInfoKey(): Flow<Boolean>
    suspend fun setUserClosedGlobalInfoKey(order: Boolean)

    // saving global message
    fun getGlobalMessage(): Flow<String>
    suspend fun setGlobalMessage(message: String)
}