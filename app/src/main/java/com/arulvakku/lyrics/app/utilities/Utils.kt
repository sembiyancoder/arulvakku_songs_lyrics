package com.arulvakku.lyrics.app.utilities

import android.content.Context
import com.arulvakku.lyrics.app.data.Category
import com.arulvakku.lyrics.app.data.Song
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}


fun getSongCategories(context: Context): List<Category> {
    val jsonFileString = context?.let { getJsonDataFromAsset(it, "songscategories.json") }
    val gson = Gson()
    val listCategory = object : TypeToken<List<Category>>() {}.type
    return gson.fromJson(jsonFileString, listCategory)
}


fun getSongList(context: Context): List<Song> {
    val jsonFileString = getJsonDataFromAsset(context, "avksongs.json")
    val gson = Gson()
    val listCategory = object : TypeToken<List<Song>>() {}.type
    return gson.fromJson(jsonFileString, listCategory)
}




