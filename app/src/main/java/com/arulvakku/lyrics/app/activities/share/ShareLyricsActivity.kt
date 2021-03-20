package com.arulvakku.lyrics.app.activities.share

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import com.arulvakku.lyrics.app.activities.share.adapter.ShareLyricsAdapter
import com.arulvakku.lyrics.app.databinding.ActivityShareLyricsBinding
import com.arulvakku.lyrics.app.utilities.MyItemDetailsLookup
import kotlinx.android.synthetic.main.activity_share_lyrics.*

class ShareLyricsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShareLyricsBinding
    private val adapter = ShareLyricsAdapter()
    private var tracker: SelectionTracker<Long>? = null

    private var temp: String = "அணி அணியாய் வாருங்கள் அன்பு மாந்தரே \n" +
            "ஆண்டவர் இயேசுவின் சாட்சி நீங்களே (2)\n" +
            "\n" +
            "\n" +
            "அன்புப்பணியாலே உலகை வெல்லுங்கள் \n" +
            "இன்ப துன்பம் எதையும் தாங்கிடுங்கள் (2) \n" +
            "எளியவர் வாழ்வில் துணைநின்று \n" +
            "இயேசுவின் சாட்சியாய் நிலைத்திருங்கள் (2)\n" +
            "\n" +
            "\n" +
            "மண்ணகத்தில் பொருளைச் சேர்க்க வேண்டாம் \n" +
            "மறைந்து ஒழிந்து போய்விடுமே (2) \n" +
            "விண்ணில் பொருளை தினம் சேர்த்து \n" +
            "இயேசுவின் சாட்சியாய் நிலைத்திருங்கள் (2)\n" +
            "\n" +
            "\n"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShareLyricsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setLyricsAdapter()
    }


    private fun setLyricsAdapter() {
        val pattern = """\n+""".toRegex()
        val words = temp.let { pattern.split(it).filter { it.isNotBlank() } }
        println(words)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
        adapter.list = words
        adapter.notifyDataSetChanged()

        tracker = SelectionTracker.Builder<Long>(
            "mySelection",
            binding.recyclerView,
            StableIdKeyProvider(binding.recyclerView),
            MyItemDetailsLookup(binding.recyclerView),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()

        adapter.tracker = tracker
    }


}