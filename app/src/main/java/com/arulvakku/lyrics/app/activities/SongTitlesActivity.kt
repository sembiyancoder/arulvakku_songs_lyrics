package com.arulvakku.lyrics.app.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.adapters.TitlesAdapter
import com.arulvakku.lyrics.app.data.Song
import com.arulvakku.lyrics.app.utilities.getJsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sembiyan.songs.app.listeners.TitleCellClickListener
import kotlinx.android.synthetic.main.activity_main.*

class SongTitlesActivity : AppCompatActivity(), TitleCellClickListener {
    lateinit var categoryName: String
    private lateinit var titles: List<Song>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_titels)
        categoryName = intent.getStringExtra("category_name")
        supportActionBar?.title = categoryName
        prepareSongTitles()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.title_menu, menu)
        return true
    }


    private fun prepareSongTitles() {
        val jsonFileString = getJsonDataFromAsset(applicationContext, "avksongs.json")
        val gson = Gson()
        val listCategory = object : TypeToken<List<Song>>() {}.type
        var allTitles: List<Song> = gson.fromJson(jsonFileString, listCategory)
        titles =
            allTitles.filter { s -> s.category == categoryName } // filtering songs with category name
        setCategoriesAdapter()
    }


    fun setCategoriesAdapter() {
        recyclerView?.apply {
            layoutManager = LinearLayoutManager(this@SongTitlesActivity)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )
            adapter = TitlesAdapter(this@SongTitlesActivity, titles, this@SongTitlesActivity)
        }
    }

    override fun onTitleCellClickListener(categoryName: String, title: String, song: String) {
        val intent = Intent(this, SongTitlesActivity::class.java)
        intent.putExtra("category_name", categoryName)
        intent.putExtra("title", title)
        intent.putExtra("song", song)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        return when (id) {
            R.id.filter -> {
                startActivity(Intent(applicationContext, AboutActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}