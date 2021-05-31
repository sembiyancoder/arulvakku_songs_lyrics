package com.arulvakku.lyrics.app.data.api

import com.arulvakku.lyrics.app.ui.view.home.category.network.SongCategoryNetworkEntity
import com.arulvakku.lyrics.app.ui.view.home.song.network.SongNetworkEntity
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getSongCategories(): Response<SongCategoryNetworkEntity> =
        apiService.getSongCategories()

    override suspend fun getSongs(): Response<SongNetworkEntity> =
        apiService.getSongs()

}