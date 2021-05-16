package com.arulvakku.lyrics.app.di

import android.content.Context
import androidx.room.Room
import com.arulvakku.lyrics.app.data.room.AppDatabase
import com.arulvakku.lyrics.app.data.room.dao.SongDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * @author bsoft-61 on 15/2/21.
 * */
@InstallIn(SingletonComponent::class)
@Module
object RoomModule {
    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideSongDAO(database: AppDatabase): SongDao {
        return database.songDao()
    }


}