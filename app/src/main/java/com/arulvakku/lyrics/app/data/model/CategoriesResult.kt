package com.arulvakku.lyrics.app.data.model

import java.io.Serializable

data class CategoriesResult(
    val sCategory: String,
    val sCategoryId: Int,
    val sColorCode: String,
    val sCount: Int
) : Serializable