package com.arulvakku.lyrics.app.data.repository

import com.arulvakku.lyrics.app.data.api.ApiHelper
import com.arulvakku.lyrics.app.ui.view.home.category.network.networkentities.CategoriesResult
import com.arulvakku.lyrics.app.ui.view.home.song.network.networkentities.SongResult
import com.arulvakku.lyrics.app.ui.view.home.category.cache.SongCategoryCacheMapper
import com.arulvakku.lyrics.app.ui.view.home.category.cache.SongCategoryDao
import com.arulvakku.lyrics.app.ui.view.home.category.network.networkentities.SongCategoryNetworkEntity
import com.arulvakku.lyrics.app.ui.view.home.category.network.SongCategoryNetworkMapper
import com.arulvakku.lyrics.app.ui.view.home.song.cache.SongCacheMapper
import com.arulvakku.lyrics.app.ui.view.home.song.cache.SongDao
import com.arulvakku.lyrics.app.ui.view.home.song.network.networkentities.SongNetworkEntity
import com.arulvakku.lyrics.app.ui.view.home.song.network.SongNetworkMapper
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
        // if(response.body()!=null) response.body().IsTransactionDone else false
        val isSuccess: Boolean = response.body()?.IsTransactionDone ?: false
        if (isSuccess) { // Insert into database if the response is true
            val list: List<CategoriesResult> = response.body()?.Result ?: emptyList()
            val songCategory = songCategoryNetworkMapper.mapFromEntityList(list)
            songCategoryDao.insertSongCategoryList(
                songCategoryCacheMapper.mapToEntityList(
                    songCategory
                )
            )
        }

        return response
    }

    suspend fun getSongs(): Response<SongNetworkEntity> {
        val response = apiHelper.getSongs()

        // if(response.body()!=null) response.body().IsTransactionDone else false
        val isSuccess: Boolean = response.body()?.IsTransactionDone ?: false
        if (isSuccess) { // Insert into database if the response is true
            val list: List<SongResult> = response.body()?.Result ?: emptyList()
            val songs = songNetworkMapper.mapFromEntityList(list)
            songDao.insertSongList(songCacheMapper.mapToEntityList(songs))
        }

        return response
    }

}