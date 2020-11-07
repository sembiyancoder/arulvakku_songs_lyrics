package com.arulvakku.lyrics.app.activities

import android.os.Bundle
import com.arulvakku.lyrics.app.R
import kotlinx.android.synthetic.main.activity_lyrics_screen.*

class LyricsScreen : BaseActivity() {

    private val  title by lazy {
        intent.getStringExtra("title")
    }

    private val song by lazy {
        intent.getStringExtra("song")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyrics_screen)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        setData()

    }

    /**
     * set title and song data
     */
    private fun setData() {
        supportActionBar?.title = title
        lyricsTExt.text = song

    }
}