package com.arulvakku.lyrics.app.activities.category

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.activities.home.adapter.TitlesAdapter
import com.arulvakku.lyrics.app.activities.lyrics.LyricsActivity
import com.arulvakku.lyrics.app.data.Song
import com.arulvakku.lyrics.app.databinding.ActivitySongTitelsBinding
import com.arulvakku.lyrics.app.utilities.getJsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sembiyan.songs.app.listeners.RecyclerOnRowItemClickListener


class SongTitlesActivity : AppCompatActivity(), RecyclerOnRowItemClickListener {

    private lateinit var titles: List<Song>
    private var filterTitles: MutableList<String> = mutableListOf<String>()
    lateinit var mSongTitlesAdapter: TitlesAdapter
    private lateinit var binding: ActivitySongTitelsBinding
    private lateinit var searchView: SearchView

    companion object {
        var pos: Int = -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongTitelsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = intent.getStringExtra("category_name")


        prepareSongTitles()
    }


    private fun prepareSongTitles() {
        val jsonFileString = getJsonDataFromAsset(applicationContext, "avksongs.json")
        val gson = Gson()
        val listCategory = object : TypeToken<List<Song>>() {}.type
        var allTitles: List<Song> = gson.fromJson(jsonFileString, listCategory)
        titles =
            allTitles.filter { s -> s.sCategory == intent.getStringExtra("category_name") } // filtering songs with category name

        titles.forEach {
            val firstLetter = it.sTitle.substring(0, 1)
            filterTitles.add(firstLetter)
        }
        setSongTitleAdapter()
    }


    private fun setSongTitleAdapter() {
        mSongTitlesAdapter = TitlesAdapter(
            this@SongTitlesActivity,
            titles.sortedBy { it.sTitle.toString() },
            this@SongTitlesActivity
        )
        binding.songTitleRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.songTitleRecyclerView.setHasFixedSize(true)
        binding.songTitleRecyclerView.adapter = mSongTitlesAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = searchItem?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mSongTitlesAdapter.filter.filter(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            if (Intent.ACTION_SEARCH == intent.getAction()) {
                val query = intent.getStringExtra(SearchManager.QUERY)
                Toast.makeText(this, "" + query, Toast.LENGTH_SHORT).show()
                searchView.setQuery(query, false)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        return when (id) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    override fun onItemRowListener(position: Int, song: Song) {
        val intent = Intent(this, LyricsActivity::class.java)
        intent.putExtra("position", song.sSongId)
        intent.putExtra("category_name", song.sCategory)
        intent.putExtra("title", song.sTitle)
        intent.putExtra("song", song.sSong)
        startActivity(intent)
    }

}