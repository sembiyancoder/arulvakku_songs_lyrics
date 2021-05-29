package com.arulvakku.lyrics.app.data.api

import com.arulvakku.lyrics.app.data.model.Song
import com.arulvakku.lyrics.app.data.model.SongCategory
import com.arulvakku.lyrics.app.ui.songCategory.SongCategoryNetworkEntity
import com.arulvakku.lyrics.app.ui.songList.SongNetworkEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("GetSongsCategoryList")
    suspend fun getSongCategories(): Response<SongCategoryNetworkEntity>

    @Headers("Content-Type: application/json")
    @GET("GetSongsList")
    suspend fun getSongs(): Response<SongNetworkEntity>
}