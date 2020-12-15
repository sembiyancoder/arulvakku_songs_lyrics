package com.arulvakku.lyrics.app.activities.lyrics.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.data.Song
import com.arulvakku.lyrics.app.databinding.FragmentLyricsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1_LYRICS = "param1"
private const val ARG_PARAM2_TITLE = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LyricsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LyricsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var paramLyrics: String? = null
    private var paramTitle: String? = null

    private lateinit var binding: FragmentLyricsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramLyrics = it.getString(ARG_PARAM1_LYRICS)
            paramTitle = it.getString(ARG_PARAM2_TITLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lyrics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLyricsBinding.bind(view)
        binding.txtLyrics.text = paramLyrics
    }

    companion object {
        @JvmStatic
        fun newInstance(song: Song) =
            LyricsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1_LYRICS, song.song)
                    putString(ARG_PARAM2_TITLE, song.title)
                }
            }
    }
}