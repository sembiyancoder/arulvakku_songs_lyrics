package com.arulvakku.lyrics.app.di

import com.arulvakku.lyrics.app.ui.song.service.SongService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Create Network related utilities
 */
@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    /**
     * Create reference for the [Gson]
     */
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    /**
     * Construct your base URL
     */
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("http://arulvakku.binaryexpertsystems.com/Arulvakku/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    /**
     * Create reference for [SongService]
     */
    @Singleton
    @Provides
    fun provideSongsService(retrofit: Retrofit.Builder): SongService {
        return retrofit
            .build()
            .create(SongService::class.java)
    }

}
