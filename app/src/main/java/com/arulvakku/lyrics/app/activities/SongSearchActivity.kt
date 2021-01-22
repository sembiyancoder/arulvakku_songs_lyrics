package com.arulvakku.lyrics.app.activities


import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.activities.home.adapter.TitlesAdapter
import com.arulvakku.lyrics.app.activities.lyrics.LyricsActivity
import com.arulvakku.lyrics.app.data.Song
import com.arulvakku.lyrics.app.databinding.ActivitySongSearchBinding
import com.arulvakku.lyrics.app.utilities.getSongList
import com.sembiyan.songs.app.listeners.TitleCellClickListener

class SongSearchActivity : AppCompatActivity(), TitleCellClickListener {

    lateinit var mSongTitlesAdapter: TitlesAdapter
    private lateinit var titles: List<Song>
    private lateinit var binding: ActivitySongSearchBinding
    private lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()
    }

    private fun setAdapter() {
        titles = getSongList(this)

        mSongTitlesAdapter = TitlesAdapter(
            this,
            titles.sortedBy { it.sSong.toString() }, this
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
        searchItem.expandActionView()
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

    override fun onTitleCellClickListener(position: Int, song: Song) {
        val intent = Intent(this, LyricsActivity::class.java)
        intent.putExtra("position", song.sSongId)
        intent.putExtra("category_name", song.sCategory)
        intent.putExtra("title", song.sTitle)
        intent.putExtra("song", song.sSong)
        startActivity(intent)
    }

}