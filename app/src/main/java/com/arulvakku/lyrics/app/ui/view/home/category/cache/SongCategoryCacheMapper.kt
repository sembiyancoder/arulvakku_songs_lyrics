package com.arulvakku.lyrics.app.ui.view.home.category.cache

import com.arulvakku.lyrics.app.utilities.ArulvakkuMapper
import com.arulvakku.lyrics.app.ui.view.home.model.SongCategoryModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongCategoryCacheMapper @Inject constructor():
    ArulvakkuMapper<SongCategoryCacheEntity, SongCategoryModel> {

    fun mapFromEntityList(entities: List<SongCategoryCacheEntity>): List<SongCategoryModel> {
        return entities.map { mapFromEntity(it) }
    }

    fun mapToEntityList(entities: List<SongCategoryModel>): List<SongCategoryCacheEntity> {
        return entities.map { mapToEntity(it) }
    }

    override fun mapFromEntity(entity: SongCategoryCacheEntity): SongCategoryModel {
        return SongCategoryModel(
            sCategoryId =entity.sCategoryId,
            sCategory = entity.sCategoryName,
            sColorCode = entity.sColorCode,
            sCount = entity.sCount
        )
    }

    override fun mapToEntity(domainModel: SongCategoryModel): SongCategoryCacheEntity {
        return SongCategoryCacheEntity(
            sCategoryId = domainModel.sCategoryId,
            sCategoryName = domainModel.sCategory,
            sColorCode = domainModel.sColorCode,
            sCount = domainModel.sCount
        )
    }
}