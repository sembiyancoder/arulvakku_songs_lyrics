package com.arulvakku.lyrics.app.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.adapters.TitlesAdapter
import com.arulvakku.lyrics.app.adapters.TitlesFilterAdapter
import com.arulvakku.lyrics.app.data.Song
import com.arulvakku.lyrics.app.utilities.getJsonDataFromAsset
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sembiyan.songs.app.listeners.CellFilterClickListener
import com.sembiyan.songs.app.listeners.TitleCellClickListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_bottom_sheet.*

class SongTitlesActivity : BaseActivity(), TitleCellClickListener, CellFilterClickListener {
    lateinit var categoryName: String
    private lateinit var titles: List<Song>
    private var filterTitles: MutableList<String> = mutableListOf<String>()
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    lateinit var mTitleRecyclerView: RecyclerView
    lateinit var mFilterRecyclerView: RecyclerView
    lateinit var mSongTitlesAdapter: TitlesAdapter
    lateinit var mSongFilterTitleAdapter: TitlesFilterAdapter
    lateinit var mClearButton: MaterialButton


    companion object {
        var pos: Int = -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_titels)
        categoryName = intent.getStringExtra("category_name")
        supportActionBar?.title = categoryName
        inflateXMLView();
        prepareSongTitles()

    }

    private fun inflateXMLView() {
        mClearButton = findViewById(R.id.btnClearFilter);
        bottomSheetBehavior = BottomSheetBehavior.from<LinearLayout>(persistent_bottom_sheet)

        mTitleRecyclerView = findViewById(R.id.recyclerView)
        mTitleRecyclerView.layoutManager = LinearLayoutManager(this)
        mTitleRecyclerView.setHasFixedSize(true)

        mFilterRecyclerView = findViewById(R.id.filterRecyclerView)
        mFilterRecyclerView.layoutManager = GridLayoutManager(this, 4)
        mFilterRecyclerView.setHasFixedSize(true)


        mClearButton.setOnClickListener {
            mSongTitlesAdapter.filter.filter("")
            expandCollapseSheet()
            pos = -1
            mSongFilterTitleAdapter.notifyDataSetChanged()
        }
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

        titles.forEach {
            val firstLetter = it.title.substring(0, 1)
            filterTitles.add(firstLetter)
        }

        setTitleAdapter()
        setFilterAdapter()
    }


    private fun setTitleAdapter() {
        mSongTitlesAdapter = TitlesAdapter(this@SongTitlesActivity, titles, this@SongTitlesActivity)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recyclerView.adapter = mSongTitlesAdapter
    }

    fun setFilterAdapter() {
        mSongFilterTitleAdapter = TitlesFilterAdapter(
            this@SongTitlesActivity,
            filterTitles.distinct(),
            this@SongTitlesActivity
        )
        mFilterRecyclerView.adapter = mSongFilterTitleAdapter

    }


    override fun onTitleCellClickListener(categoryName: String, title: String, song: String) {
        val intent = Intent(this, LyricsScreenActivity::class.java)
        intent.putExtra("category_name", categoryName)
        intent.putExtra("title", title)
        intent.putExtra("song", song)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        return when (id) {
            R.id.filter -> {
                expandCollapseSheet()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun expandCollapseSheet() {
        if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        } else {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    override fun onFilterCellClickListener(item: String, position: Int) {
        mSongTitlesAdapter.filter.filter(item)
        pos = position
        mSongFilterTitleAdapter.notifyDataSetChanged()
        mClearButton.visibility = View.VISIBLE
    }

}