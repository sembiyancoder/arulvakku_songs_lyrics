package com.arulvakku.lyrics.app.ui.view.more

import android.content.Context
import javax.inject.Inject


class MoreRepository
@Inject
constructor() {
    fun readDataFromAssets(context: Context): String {
        return context.assets.open("more.json").bufferedReader().use {
            it.readText()
        }
    }
}