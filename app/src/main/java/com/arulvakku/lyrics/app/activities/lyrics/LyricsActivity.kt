package com.arulvakku.lyrics.app.activities.lyrics

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.activities.lyrics.adapter.LyricsPagerAdapter
import com.arulvakku.lyrics.app.data.Song
import com.arulvakku.lyrics.app.databinding.ActivityLyricsBinding
import com.arulvakku.lyrics.app.utilities.getJsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LyricsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLyricsBinding
    private lateinit var pagerAdapter: LyricsPagerAdapter
    private lateinit var songs: List<Song>
    private var songId: Int = 0
    private var position: Int = 0
    private lateinit var categoryName: String
    private lateinit var currentSong: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLyricsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        songId = intent.getIntExtra("position", 0)
        categoryName = intent.getStringExtra("category_name")
        getSongList();

        findPosition();

        pagerAdapter = LyricsPagerAdapter(supportFragmentManager, songs)
        binding.viewPager.adapter = pagerAdapter

        binding.viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                updateToolbarTitle(songs[position].sTitle)
                currentSong = songs[position] // current song
            }

            override fun onPageSelected(position: Int) {

            }

        })

        binding.viewPager.currentItem = position
    }


    private fun updateToolbarTitle(songTitle: String) {
        supportActionBar?.title = songTitle
    }

    private fun getSongList() {
        val jsonFileString = getJsonDataFromAsset(applicationContext, "avksongs.json")
        val gson = Gson()
        val listCategory = object : TypeToken<List<Song>>() {}.type
        var allTitles: List<Song> = gson.fromJson(jsonFileString, listCategory)
        songs = allTitles.filter { s -> s.sCategory == categoryName }
            .sortedBy { it.sTitle.toString() } // filtering songs with category name
    }

    private fun findPosition() {
        for (item in songs) {
            if (item.sSongId == songId) {
                position = songs.indexOf(item)
                break
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.lyrics_menu, menu)
        return true
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        return when (id) {
            R.id.share -> {
                shareSong(currentSong.sSong);
                true
            }
            R.id.copy -> {
                copyTextToClipboard(currentSong.sSong)
                true
            }
            R.id.settings -> {
                //openBottomSheet()
                true
            }
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareSong(song: String) {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, song);
        startActivity(Intent.createChooser(shareIntent, getString(R.string.send_to)))
    }

    /**
     * Copy songs to clipboard
     */
    private fun copyTextToClipboard(song: String) {
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", song)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_LONG).show()
    }

}
