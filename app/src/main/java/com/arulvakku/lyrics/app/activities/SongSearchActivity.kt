package com.arulvakku.lyrics.app.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arulvakku.lyrics.app.adapters.TitlesAdapter
import com.arulvakku.lyrics.app.data.Song
import com.arulvakku.lyrics.app.databinding.ActivitySongSearchBinding
import com.arulvakku.lyrics.app.utilities.getJsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sembiyan.songs.app.listeners.TitleCellClickListener

class SongSearchActivity : AppCompatActivity(), TitleCellClickListener {

    private lateinit var binding: ActivitySongSearchBinding
    private lateinit var titles: List<Song>
    lateinit var mSongTitlesAdapter: TitlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareSongTitles()
        setSongTitleAdapter()
    }


    private fun prepareSongTitles() {
        val jsonFileString = getJsonDataFromAsset(applicationContext, "avksongs.json")
        val gson = Gson()
        val listCategory = object : TypeToken<List<Song>>() {}.type
        titles = gson.fromJson(jsonFileString, listCategory)
    }

    private fun setSongTitleAdapter() {
        mSongTitlesAdapter = TitlesAdapter(
            this@SongSearchActivity,
            titles.sortedBy { it.title.toString() },
            this@SongSearchActivity
        )
        binding.songTitleRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.songTitleRecyclerView.setHasFixedSize(true)
        binding.songTitleRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        binding.songTitleRecyclerView.adapter = mSongTitlesAdapter
    }

    override fun onTitleCellClickListener(categoryName: String, title: String, lyrics: String) {
        val intent = Intent(this, LyricsScreenActivity::class.java)
        intent.putExtra("category_name", categoryName)
        intent.putExtra("title", title)
        intent.putExtra("song", lyrics)
        startActivity(intent)
    }


}