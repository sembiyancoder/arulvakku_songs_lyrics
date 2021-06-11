package com.arulvakku.lyrics.app.data.repository

import com.arulvakku.lyrics.app.ui.view.home.category.SongCategoryModel
import com.arulvakku.lyrics.app.ui.view.home.category.cache.SongCategoryCacheMapper
import com.arulvakku.lyrics.app.ui.view.home.category.cache.SongCategoryDao
import com.arulvakku.lyrics.app.ui.view.home.song.SongModel
import com.arulvakku.lyrics.app.ui.view.home.song.cache.SongCacheMapper
import com.arulvakku.lyrics.app.ui.view.home.song.cache.SongDao
import com.arulvakku.lyrics.app.ui.view.favourite.cache.PlaylistSongCrossRef
import com.arulvakku.lyrics.app.ui.view.favourite.cache.PlaylistWithSongs
import javax.inject.Inject

class DatabaseRepository
@Inject
constructor(
    private val songDao: SongDao,
    private val categoryDao: SongCategoryDao,
    private val songCategoryCacheMapper: SongCategoryCacheMapper,
    private val songCacheMapper: SongCacheMapper,
) {

    suspend fun getSongCount(): Long = songDao.getSongCount()


    suspend fun getCategoryCount(): Long = categoryDao.getCategoryCount()

    suspend fun getCategories(): List<SongCategoryModel> {
        val data = categoryDao.getSongCategoryList()
        return songCategoryCacheMapper.mapFromEntityList(data)
    }

    suspend fun getSongs(): List<SongModel> {
        val data = songDao.getSongList()
        return songCacheMapper.mapFromEntityList(data)
    }

    suspend fun getFavouriteSongs(): PlaylistWithSongs {
        return songDao.getFavouriteSongs()
    }
    suspend fun setFavouriteSong(songId: Int):Long {
        return songDao.setFavouriteSong(PlaylistSongCrossRef(sSongId = songId.toLong(),playlistId = 1))
    }
    suspend fun removeFavouriteSong(songId: Int):Long {
        return songDao.removeFavouriteSong(PlaylistSongCrossRef(sSongId = songId.toLong(),playlistId = 1)).toLong()
    }

    suspend fun isFavouriteSong(songId: Int):Boolean{
        return songDao.isFavouriteSong(songId)
    }

    suspend fun getSongsByCategory(categoryId: Int): List<SongModel> {
        val data = songDao.getSongListByCategory(categoryId)
        return songCacheMapper.mapFromEntityList(data)
    }

}