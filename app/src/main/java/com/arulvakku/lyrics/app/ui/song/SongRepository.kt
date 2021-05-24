package com.arulvakku.lyrics.app.ui.song

import com.arulvakku.lyrics.app.ui.song.service.networkmapper.SongNetworkMapper
import com.arulvakku.lyrics.app.ui.song.service.SongService
import com.arulvakku.lyrics.app.ui.song.cache.dao.SongDao
import com.arulvakku.lyrics.app.ui.song.cache.SongCacheMapper
import com.arulvakku.lyrics.app.ui.song.model.Song
import com.arulvakku.lyrics.app.utilities.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject


class SongRepository
@Inject
constructor(
    private val service: SongService,
    private val dao: SongDao,
    private val cacheMapper: SongCacheMapper,
    private val networkMapper: SongNetworkMapper
) {

    suspend fun getAllSongs(): Flow<Resource<List<Song>>> = flow {
        emit(Resource.loading())
        try {
            val networkSongs = service.getSongs() // Get songs from web service
            /**
             * Convert from [SongNetworkEntity] to [Song]
             */
            val songs = networkMapper.mapFromEntityList(networkSongs.result?: emptyList())

            // Insert all songs into room database
            for (song in songs) {
                dao.insert(cacheMapper.mapToEntity(song))
            }
//            val cachedBlogs = dao.getAllSongs() //We can skip this
            // emit (return) all songs
            emit(Resource.success(songs))
        } catch (e: Exception) {
            emit(Resource.error(e.toString(), null))
        }
    }
}