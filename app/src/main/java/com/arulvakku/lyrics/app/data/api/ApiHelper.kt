package com.arulvakku.lyrics.app.data.api

import com.arulvakku.lyrics.app.ui.view.home.category.network.SongCategoryNetworkEntity
import com.arulvakku.lyrics.app.ui.view.home.song.network.SongNetworkEntity
import retrofit2.Response

interface ApiHelper {
    suspend fun getSongCategories(): Response<SongCategoryNetworkEntity>
    suspend fun getSongs(): Response<SongNetworkEntity>
}