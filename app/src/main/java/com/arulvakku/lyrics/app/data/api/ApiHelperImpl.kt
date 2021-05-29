package com.arulvakku.lyrics.app.data.api

import com.arulvakku.lyrics.app.data.model.Song
import com.arulvakku.lyrics.app.data.model.SongCategory
import com.arulvakku.lyrics.app.ui.songCategory.SongCategoryNetworkEntity
import com.arulvakku.lyrics.app.ui.songList.SongNetworkEntity
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getSongCategories(): Response<SongCategoryNetworkEntity> =
        apiService.getSongCategories()

    override suspend fun getSongs(): Response<SongNetworkEntity> =
        apiService.getSongs()

}