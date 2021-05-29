package com.arulvakku.lyrics.app.ui.songCategory

import com.arulvakku.lyrics.app.data.model.CategoriesResult
import com.arulvakku.lyrics.app.data.model.SongCategory
import com.arulvakku.lyrics.app.ui.commonmapper.ArulvakkuMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongCategoryNetworkMapper @Inject constructor() : ArulvakkuMapper<CategoriesResult,SongCategoryModel> {
    override fun mapFromEntity(entity: CategoriesResult): SongCategoryModel {
       return SongCategoryModel(
           sCategoryId =entity.sCategoryId,
           sCategory = entity.sCategory,
           sColorCode = entity.sColorCode,
           sCount = entity.sCount
       )
    }

    override fun mapToEntity(domainModel: SongCategoryModel): CategoriesResult {
        return CategoriesResult(
            sCategoryId = domainModel.sCategoryId,
            sCategory = domainModel.sCategory,
            sColorCode = domainModel.sColorCode,
            sCount = domainModel.sCount
        )
    }

    fun mapFromEntityList(entities: List<CategoriesResult>): List<SongCategoryModel> {
        return entities.map { mapFromEntity(it) }
    }

    fun mapToEntityList(entities: List<SongCategoryModel>): List<CategoriesResult> {
        return entities.map { mapToEntity(it) }
    }
}