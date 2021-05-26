package com.arulvakku.lyrics.app.ui.main.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.data.model.SongCategory
import com.arulvakku.lyrics.app.utilities.PreferenceStorage
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var prefStorage: PreferenceStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        * sample retrieving from data store
        * */
        lifecycleScope.launchWhenCreated {
            val songCategory = prefStorage.songCategory.firstOrNull()
            songCategory?.let {
                Log.d("test", it)
                val gson = Gson()
                val obj: SongCategory = gson.fromJson(it, SongCategory::class.java)
            }
        }
    }
}