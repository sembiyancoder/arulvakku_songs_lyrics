package com.arulvakku.lyrics.app.ui.view.favourite.cache

import com.arulvakku.lyrics.app.ui.view.home.category.cache.SongCategoryCacheEntity
import com.arulvakku.lyrics.app.ui.view.home.model.SongCategoryModel
import com.arulvakku.lyrics.app.ui.view.home.song.SongModel
import com.arulvakku.lyrics.app.ui.view.home.song.cache.SongCacheEntity
import com.arulvakku.lyrics.app.utilities.ArulvakkuMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CacheMapper @Inject constructor() : ArulvakkuMapper<SongModel, SongCacheEntity> {
    override fun mapFromEntity(entity: SongModel): SongCacheEntity {
        return SongCacheEntity(
            sSongId = entity.sSongId,
            sCategory = entity.sCategory,
            sCategoryId = entity.sCategoryId,
            sSong = entity.sSong,
            sTitle = entity.sTitle
        )
    }

    override fun mapToEntity(domainModel: SongCacheEntity): SongModel {
        return SongModel(
            sSongId = domainModel.sSongId,
            sCategory = domainModel.sCategory,
            sCategoryId = domainModel.sCategoryId,
            sSong = domainModel.sSong,
            sTitle = domainModel.sTitle
        )
    }

    fun mapFromEntityList(entities: List<SongModel>): List<SongCacheEntity> {
        return entities.map { mapFromEntity(it) }
    }

    fun mapToEntityList(entities: List<SongCacheEntity>): List<SongModel> {
        return entities.map { mapToEntity(it) }
    }
}