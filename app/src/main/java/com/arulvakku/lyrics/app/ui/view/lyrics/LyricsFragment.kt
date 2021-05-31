package com.arulvakku.lyrics.app.ui.view.lyrics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arulvakku.lyrics.app.databinding.LyricsFragmentBinding
import com.arulvakku.lyrics.app.ui.view.home.song.SongModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LyricsFragment : Fragment() {

    /*companion object {
        fun newInstance(songModel: SongModel) = LyricsFragment()
    }*/

    companion object {
        fun newInstance(songModel: SongModel): Fragment {
            val fragment = LyricsFragment()
            val args = Bundle()
            args.putString("lyrics", songModel.sSong)
            fragment.arguments = args
            return fragment
        }
    }


    private lateinit var viewModel: LyricsViewModel
    private lateinit var binding: LyricsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LyricsFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LyricsViewModel::class.java)
        binding.textLyrics.text = requireArguments().getString("lyrics")
    }

}