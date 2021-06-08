package com.arulvakku.lyrics.app.ui.home.song.cache

import com.arulvakku.lyrics.app.utilities.ArulvakkuMapper
import com.arulvakku.lyrics.app.ui.home.song.SongModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongCacheMapper @Inject constructor() : ArulvakkuMapper<SongCacheEntity, SongModel> {
    override fun mapFromEntity(entity: SongCacheEntity): SongModel {
        return SongModel(
            sSongId = entity.sSongId,
            sSong = entity.sSong,
            sCategory = entity.sCategory,
            sCategoryId = entity.sCategoryId,
            sTitle = entity.sTitle
        )
    }

    override fun mapToEntity(domainModel: SongModel): SongCacheEntity {
        return SongCacheEntity(
            sSongId = domainModel.sSongId,
            sSong = domainModel.sSong,
            sCategoryId = domainModel.sCategoryId,
            sCategory = domainModel.sCategory,
            sTitle = domainModel.sTitle
        )
    }

    fun mapFromEntityList(entities: List<SongCacheEntity>): List<SongModel> {
        return entities.map { mapFromEntity(it) }
    }

    fun mapToEntityList(entities: List<SongModel>): List<SongCacheEntity> {
        return entities.map { mapToEntity(it) }
    }
}