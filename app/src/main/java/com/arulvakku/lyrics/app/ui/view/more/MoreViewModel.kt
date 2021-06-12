package com.arulvakku.lyrics.app.ui.view.more

import android.content.Context
import androidx.lifecycle.ViewModel
import com.arulvakku.lyrics.app.ui.view.more.model.MoreData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    private val moreRepository: MoreRepository
) : ViewModel() {

    lateinit var data: MutableList<MoreData>

    fun readDataFromAssets(context: Context) {
        val map = object : TypeToken<MutableList<MoreData>>() {}.type
        data = Gson().fromJson(moreRepository.readDataFromAssets(context), map);
    }
}