package com.arulvakku.lyrics.app.data.repository

import com.arulvakku.lyrics.app.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getSongCategories() = apiHelper.getSongCategories()
    suspend fun getSongs() = apiHelper.getSongs()

}