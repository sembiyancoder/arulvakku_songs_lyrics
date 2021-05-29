package com.arulvakku.lyrics.app.data.repository

import com.arulvakku.lyrics.app.data.api.ApiHelper
import com.arulvakku.lyrics.app.ui.songCategory.SongCategoryCacheMapper
import com.arulvakku.lyrics.app.ui.songCategory.SongCategoryDao
import com.arulvakku.lyrics.app.ui.songCategory.SongCategoryNetworkEntity
import com.arulvakku.lyrics.app.ui.songCategory.SongCategoryNetworkMapper
import com.arulvakku.lyrics.app.ui.songList.SongCacheMapper
import com.arulvakku.lyrics.app.ui.songList.SongDao
import com.arulvakku.lyrics.app.ui.songList.SongNetworkEntity
import com.arulvakku.lyrics.app.ui.songList.SongNetworkMapper
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val songCategoryNetworkMapper: SongCategoryNetworkMapper,
    private val songCategoryCacheMapper: SongCategoryCacheMapper,
    private val songCategoryDao: SongCategoryDao,
    private val songNetworkMapper: SongNetworkMapper,
    private val songCacheMapper: SongCacheMapper,
    private val songDao: SongDao
) {

    suspend fun getSongCategories(): Response<SongCategoryNetworkEntity> {
        val response = apiHelper.getSongCategories()
        val songCategory = songCategoryNetworkMapper.mapFromEntityList(response.body()!!.Result)
        songCategoryDao.insertSongCategoryList(songCategoryCacheMapper.mapToEntityList(songCategory))
        return response
    }

    suspend fun getSongs(): Response<SongNetworkEntity> {
        val response = apiHelper.getSongs()
        val songs = songNetworkMapper.mapFromEntityList(response.body()!!.Result)
        songDao.insertSongList(songCacheMapper.mapToEntityList(songs))
        return response
    }

}