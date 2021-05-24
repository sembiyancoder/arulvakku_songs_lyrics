package com.arulvakku.lyrics.app.ui.song.service

import com.arulvakku.lyrics.app.ui.song.service.networkentity.SongResponseNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Headers



interface SongService {
    // Service to get all songs
    @Headers("Content-Type: application/json")
    @GET("GetSongsList")
    suspend fun getSongs(): SongResponseNetworkEntity
}