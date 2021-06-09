package com.arulvakku.lyrics.app.ui.view.home.category.network

import com.arulvakku.lyrics.app.ui.view.home.category.network.networkentities.CategoriesResult
import com.arulvakku.lyrics.app.utilities.ArulvakkuMapper
import com.arulvakku.lyrics.app.ui.view.home.category.SongCategoryModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongCategoryNetworkMapper @Inject constructor() :
    ArulvakkuMapper<CategoriesResult, SongCategoryModel> {
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