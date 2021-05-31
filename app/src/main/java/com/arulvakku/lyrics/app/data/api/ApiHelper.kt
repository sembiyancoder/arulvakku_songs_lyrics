package com.arulvakku.lyrics.app.data.api

import com.arulvakku.lyrics.app.ui.view.home.category.network.networkentities.SongCategoryNetworkEntity
import com.arulvakku.lyrics.app.ui.view.home.song.network.networkentities.SongNetworkEntity
import retrofit2.Response

interface ApiHelper {
    suspend fun getSongCategories(): Response<SongCategoryNetworkEntity>
    suspend fun getSongs(): Response<SongNetworkEntity>
}