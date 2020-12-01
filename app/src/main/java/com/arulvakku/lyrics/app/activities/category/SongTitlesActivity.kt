package com.arulvakku.lyrics.app.activities.category

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.activities.BaseActivity
import com.arulvakku.lyrics.app.activities.lyrics.LyricsScreenActivity
import com.arulvakku.lyrics.app.activities.home.adapter.TitlesAdapter
import com.arulvakku.lyrics.app.adapters.TitlesFilterAdapter
import com.arulvakku.lyrics.app.data.Song
import com.arulvakku.lyrics.app.databinding.ActivitySongTitelsBinding
import com.arulvakku.lyrics.app.utilities.getJsonDataFromAsset
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sembiyan.songs.app.listeners.CellFilterClickListener
import com.sembiyan.songs.app.listeners.TitleCellClickListener

class SongTitlesActivity : BaseActivity(), TitleCellClickListener, CellFilterClickListener {

    private lateinit var titles: List<Song>
    private var filterTitles: MutableList<String> = mutableListOf<String>()
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    lateinit var mSongTitlesAdapter: TitlesAdapter
    lateinit var mSongFilterTitleAdapter: TitlesFilterAdapter
    private lateinit var binding: ActivitySongTitelsBinding

    companion object {
        var pos: Int = -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongTitelsBinding.inflate(layoutInflater)

        setContentView(binding.root)
        supportActionBar?.title = intent.getStringExtra("category_name")
        inflateXMLView()
        prepareSongTitles()

        binding.countrySearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mSongTitlesAdapter.filter.filter(newText)
                return false
            }

        })


        binding.bottomLayout.imgClose.setOnClickListener {
            expandCollapseSheet()
        }

    }

    private fun inflateXMLView() {
        bottomSheetBehavior = BottomSheetBehavior.from<LinearLayout>(binding.bottomLayout.persistentBottomSheet)

        binding.bottomLayout.clearButton.setOnClickListener {
            clearFilterSelection()
        }


        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // handle onSlide
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED ->  binding.bg.visibility=View.GONE
                    BottomSheetBehavior.STATE_EXPANDED ->  binding.bg.visibility=View.VISIBLE
                }
            }
        })

    }


    private fun clearFilterSelection() {
        mSongTitlesAdapter.filter.filter("")
        pos = -1
        mSongFilterTitleAdapter.notifyDataSetChanged()
        expandCollapseSheet()
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
        titles = allTitles.filter { s -> s.category == intent.getStringExtra("category_name") } // filtering songs with category name
        titles.forEach {
            val firstLetter = it.title.substring(0, 1)
            filterTitles.add(firstLetter)
        }
        setSongTitleAdapter()
        setFilterAdapter()
    }


    private fun setSongTitleAdapter() {
        mSongTitlesAdapter = TitlesAdapter(
            this@SongTitlesActivity,
            titles.sortedBy { it.title.toString() },
            this@SongTitlesActivity
        )
        binding.songTitleRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.songTitleRecyclerView.setHasFixedSize(true)
        binding.songTitleRecyclerView.adapter = mSongTitlesAdapter
    }

    private fun setFilterAdapter() {
        mSongFilterTitleAdapter = TitlesFilterAdapter(
            this@SongTitlesActivity,
            filterTitles.distinct(),
            this@SongTitlesActivity
        )
        binding.bottomLayout.filterRecyclerView.layoutManager = GridLayoutManager(this, 4)
        binding.bottomLayout.filterRecyclerView.setHasFixedSize(true)
        binding.bottomLayout.filterRecyclerView.adapter = mSongFilterTitleAdapter
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
    }

}