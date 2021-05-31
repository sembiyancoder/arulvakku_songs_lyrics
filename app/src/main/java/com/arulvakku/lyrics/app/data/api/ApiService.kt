package com.arulvakku.lyrics.app.data.api

import com.arulvakku.lyrics.app.ui.view.home.category.network.SongCategoryNetworkEntity
import com.arulvakku.lyrics.app.ui.view.home.song.network.SongNetworkEntity
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