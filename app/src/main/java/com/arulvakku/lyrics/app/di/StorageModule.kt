package com.arulvakku.lyrics.app.di

import com.arulvakku.lyrics.app.utilities.AppPrefsStorage
import com.arulvakku.lyrics.app.utilities.PreferenceStorage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class StorageModule {

    @Binds
    abstract fun providesPreferenceStorage(
        appPreferenceStorage: AppPrefsStorage
    ): PreferenceStorage
}