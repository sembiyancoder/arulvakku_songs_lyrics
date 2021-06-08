package com.arulvakku.lyrics.app.ui.lyrics

import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.databinding.LyricsFragmentBinding
import com.arulvakku.lyrics.app.ui.home.song.SongModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LyricsFragment : Fragment() {

    companion object {
        fun newInstance(songModel: SongModel): Fragment {
            val fragment = LyricsFragment()
            val args = Bundle()
            args.putString("lyrics", songModel.sSong)
            args.putString("title", songModel.sTitle)
            args.putString("category", songModel.sCategory)
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
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this).get(LyricsViewModel::class.java)
        binding.textLyrics.text = requireArguments().getString("lyrics")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.lyrics_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_make_favorite -> {

                true
            }

            R.id.action_make_share -> {
                val shareMsg = getString(
                    R.string.share_message,
                    requireArguments().getString("title"),
                    requireArguments().getString("lyrics")
                )

                val intent = ShareCompat.IntentBuilder.from(requireActivity())
                    .setType("text/plain")
                    .setText(shareMsg)
                    .intent

                if (intent.resolveActivity(requireActivity().packageManager) != null) {
                    startActivity(intent)
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}