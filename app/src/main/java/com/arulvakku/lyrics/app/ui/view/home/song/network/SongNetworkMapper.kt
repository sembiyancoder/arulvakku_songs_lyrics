package com.arulvakku.lyrics.app.ui.view.home.song.network

import com.arulvakku.lyrics.app.ui.view.home.song.network.networkentities.SongResult
import com.arulvakku.lyrics.app.utilities.ArulvakkuMapper
import com.arulvakku.lyrics.app.ui.view.home.song.SongModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongNetworkMapper @Inject constructor() : ArulvakkuMapper<SongResult, SongModel> {
    override fun mapFromEntity(entity: SongResult): SongModel {
        return SongModel(
            sSongId = entity.sSongId,
            sSong = entity.sSong,
            sCategoryId = entity.sCategoryId,
            sCategory = entity.sCategory,
            sTitle = entity.sTitle
        )
    }

    override fun mapToEntity(domainModel: SongModel): SongResult {
        return SongResult(
            sSongId = domainModel.sSongId,
            sSong = domainModel.sSong,
            sCategory = domainModel.sCategory,
            sCategoryId = domainModel.sCategoryId,
            sTitle = domainModel.sTitle
        )
    }

    fun mapFromEntityList(entities: List<SongResult>): List<SongModel> {
        return entities.map { mapFromEntity(it) }
    }

    fun mapToEntityList(entities: List<SongModel>): List<SongResult> {
        return entities.map { mapToEntity(it) }
    }
}