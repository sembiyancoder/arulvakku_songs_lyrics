package com.arulvakku.lyrics.app.di

import com.arulvakku.lyrics.app.activities.SongRepository
import com.arulvakku.lyrics.app.data.retrofit.SongNetworkMapper
import com.arulvakku.lyrics.app.data.retrofit.SongService
import com.arulvakku.lyrics.app.data.room.dao.SongDao
import com.arulvakku.lyrics.app.data.room.entities.SongCacheEntity
import com.arulvakku.lyrics.app.data.room.entities.SongCacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * @author bsoft-61 on 15/2/21.
 * */
@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {


    @Singleton
    @Provides
    fun provideRepository(
        service: SongService,
        dao: SongDao,
        cacheMapper: SongCacheMapper,
        networkMapper: SongNetworkMapper
    ): SongRepository {
        return SongRepository(service, dao, cacheMapper, networkMapper)
    }
}