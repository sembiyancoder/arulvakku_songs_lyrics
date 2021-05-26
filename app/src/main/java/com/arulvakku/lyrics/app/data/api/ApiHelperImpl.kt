package com.arulvakku.lyrics.app.data.api

import com.arulvakku.lyrics.app.data.model.Song
import com.arulvakku.lyrics.app.data.model.SongCategory
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getSongCategories(): Response<SongCategory> =
        apiService.getSongCategories()

    override suspend fun getSongs(): Response<Song> =
        apiService.getSongs()

}