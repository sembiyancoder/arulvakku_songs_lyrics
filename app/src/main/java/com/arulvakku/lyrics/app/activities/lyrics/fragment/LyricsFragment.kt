package com.arulvakku.lyrics.app.activities.lyrics.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.data.Song
import com.arulvakku.lyrics.app.databinding.FragmentLyricsBinding


private const val ARG_PARAM1_LYRICS = "param1"
private const val ARG_PARAM2_TITLE = "param2"
private const val ARG_PARAM2_CATEGORY = "param3"

class LyricsFragment : Fragment() {

    private var paramLyrics: String? = null
    private var paramTitle: String? = null
    private var paramCategory: String? = null

    private lateinit var binding: FragmentLyricsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramLyrics = it.getString(ARG_PARAM1_LYRICS)
            paramTitle = it.getString(ARG_PARAM2_TITLE)
            paramCategory = it.getString(ARG_PARAM2_CATEGORY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lyrics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLyricsBinding.bind(view)

        binding.txtLyrics.text = paramLyrics
        binding.txtSongCategory.text = paramCategory

        binding.imageShare.setOnClickListener {
            paramCategory?.let { it1 -> paramLyrics?.let { it2 -> shareSong(it1, it2) } }
        }

        val pattern = """\n+""".toRegex()
        val words = paramLyrics?.let { pattern.split(it).filter { it.isNotBlank() } }
        println(words)

    }

    companion object {
        @JvmStatic
        fun newInstance(song: Song) =
            LyricsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1_LYRICS, song.sSong)
                    putString(ARG_PARAM2_TITLE, song.sTitle.trim())
                    putString(ARG_PARAM2_CATEGORY, song.sCategory.trim())
                }
            }
    }

    private fun shareSong(category: String, song: String) {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, category + "\n\n" + song)
        startActivity(Intent.createChooser(shareIntent, getString(R.string.send_to)))
    }
}