package com.arulvakku.lyrics.app.di

import android.content.Context
import androidx.room.Room
import com.arulvakku.lyrics.app.data.database.ArulvakkuDatabase
import com.arulvakku.lyrics.app.ui.home.category.cache.SongCategoryDao
import com.arulvakku.lyrics.app.ui.home.song.cache.SongDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideArulvakkuDb(@ApplicationContext context: Context): ArulvakkuDatabase {
        return Room.databaseBuilder(
            context,
            ArulvakkuDatabase::class.java,
            ArulvakkuDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideSongCategoryDao(arulvakkuDatabase: ArulvakkuDatabase): SongCategoryDao {
        return arulvakkuDatabase.songCategoryDao()
    }

    @Singleton
    @Provides
    fun provideSongDao(arulvakkuDatabase: ArulvakkuDatabase): SongDao {
        return arulvakkuDatabase.songDao()
    }
}