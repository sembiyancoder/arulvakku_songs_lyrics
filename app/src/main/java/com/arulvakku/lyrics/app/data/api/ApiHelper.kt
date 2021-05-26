package com.arulvakku.lyrics.app.data.api

import com.arulvakku.lyrics.app.data.model.Song
import com.arulvakku.lyrics.app.data.model.SongCategory
import retrofit2.Response

interface ApiHelper {
    suspend fun getSongCategories(): Response<SongCategory>
    suspend fun getSongs(): Response<Song>
}