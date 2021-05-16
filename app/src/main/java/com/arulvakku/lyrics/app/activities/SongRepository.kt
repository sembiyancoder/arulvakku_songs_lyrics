package com.arulvakku.lyrics.app.activities

import com.arulvakku.lyrics.app.data.retrofit.SongNetworkEntity
import com.arulvakku.lyrics.app.data.retrofit.SongNetworkMapper
import com.arulvakku.lyrics.app.data.retrofit.SongService
import com.arulvakku.lyrics.app.data.room.dao.SongDao
import com.arulvakku.lyrics.app.data.room.entities.SongCacheMapper
import com.arulvakku.lyrics.app.utilities.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject


/**
 * @author bsoft-61 on 15/2/21.
 * */
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
        delay(1000)
        try {
            val networkBlogs = service.getSongs()
            val blogs = networkMapper.mapFromEntityList(networkBlogs.result?: emptyList())
            for (blog in blogs) {
                dao.insert(cacheMapper.mapToEntity(blog))
            }
            Timber.d("DATA_CONT: ${dao.getRowCount()}")
            val cachedBlogs = dao.getAllSongs()
            emit(Resource.success(cacheMapper.mapFromEntityList(cachedBlogs)))
        } catch (e: Exception) {
            emit(Resource.error(e.toString(), null))
        }
    }
}