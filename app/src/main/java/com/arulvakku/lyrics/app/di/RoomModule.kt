package com.arulvakku.lyrics.app.di

import android.content.Context
import androidx.room.Room
import com.arulvakku.lyrics.app.data.database.ArulvakkuDatabase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {
    fun provideArulvakkuDb(@ApplicationContext context: Context): ArulvakkuDatabase {
        return Room.databaseBuilder(
            context,
            ArulvakkuDatabase::class.java,
            ArulvakkuDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }


}