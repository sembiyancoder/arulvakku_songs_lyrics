package com.arulvakku.lyrics.app.data.api

import com.arulvakku.lyrics.app.data.model.Song
import com.arulvakku.lyrics.app.data.model.SongCategory
import com.arulvakku.lyrics.app.ui.songCategory.SongCategoryNetworkEntity
import com.arulvakku.lyrics.app.ui.songList.SongNetworkEntity
import retrofit2.Response

interface ApiHelper {
    suspend fun getSongCategories(): Response<SongCategoryNetworkEntity>
    suspend fun getSongs(): Response<SongNetworkEntity>
}