package com.arulvakku.lyrics.app.ui.view.lyrics

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Layout
import android.text.Spannable
import android.text.SpannableString
import android.text.style.*
import android.view.*
import androidx.core.app.ShareCompat
import androidx.core.text.toSpannable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.databinding.LyricsFragmentBinding
import com.arulvakku.lyrics.app.ui.view.home.song.SongModel
import com.arulvakku.lyrics.app.ui.viewmodels.DatabaseViewModel
import com.arulvakku.lyrics.app.utilities.RoundedBackgroundSpan
import com.arulvakku.lyrics.app.utilities.Status
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class LyricsFragment : Fragment() {

    companion object {
        fun newInstance(songModel: SongModel): Fragment {
            val fragment = LyricsFragment()
            val args = Bundle()
            args.putInt("id", songModel.sSongId ?: 0)
            args.putString("lyrics", songModel.sSong)
            args.putString("title", songModel.sTitle)
            args.putString("category", songModel.sCategory)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var favouriteMenu: MenuItem
    private lateinit var viewModel: LyricsViewModel
    private lateinit var binding: LyricsFragmentBinding
    private val databaseViewModel: DatabaseViewModel by viewModels()
    lateinit var favoriteView: LottieAnimationView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LyricsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this).get(LyricsViewModel::class.java)
        binding.textLyrics.text = requireArguments().getString("lyrics")
//        binding.txtTitle.text = requireArguments().getString("title")+" "+ requireArguments().getString("category")
        val spannableString = SpannableString(
            requireArguments().getString("title") + " " + requireArguments().getString("category")
        )


        val count = requireArguments().getString("category")!!.length
        //set the back ground color
        val backgroundSpan = BackgroundColorSpan(Color.GRAY)
        spannableString.setSpan(
            backgroundSpan,
            spannableString.length - count,
            spannableString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        //Set the text color to white
        spannableString.setSpan(
            ForegroundColorSpan(Color.WHITE), spannableString.length - count,
            spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        //Align the text to center
        spannableString.setSpan(
            AlignmentSpan.Standard(Layout.Alignment.ALIGN_NORMAL), spannableString.length - count,
            spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        //Set the text size
        spannableString.setSpan(
            RelativeSizeSpan(1.3f), 0,
            spannableString.length - count,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

       /* //Set padding
        spannableString.setSpan(
            RoundedBackgroundSpan(), spannableString.length - count,
            spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )*/
        binding.txtTitle.text = spannableString
//        binding.txtCategory.text = requireArguments().getString("category")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.lyrics_menu, menu)

        val favoriteItem = menu.findItem(R.id.action_make_favorite)
        favoriteView = favoriteItem.actionView as LottieAnimationView
        favoriteView.setAnimation(R.raw.heartpop)

        favoriteView.setOnClickListener {
            val id = requireArguments().getInt("id")
            if (favoriteView.isActivated) {
                databaseViewModel.removeFavouriteSong(id)
            } else {
                databaseViewModel.setFavouriteSong(id)
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        favouriteMenu = menu.findItem(R.id.action_make_favorite)

        val id = requireArguments().getInt("id")
        databaseViewModel.isFavouriteSongs(id)
    }

    @SuppressLint("QueryPermissionsNeeded")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            /* R.id.action_make_favorite -> {
                 val id = requireArguments().getInt("id")
                 databaseViewModel.setFavouriteSong(id)
                 true
             }*/
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


    private fun subscribe() {
        databaseViewModel.setFavouriteSongResult.observe(viewLifecycleOwner) { it ->
            when (it.status) {
                Status.LOADING -> {
                    Timber.d("loading...")
                }
                Status.SUCCESS -> {
                    Timber.d("success: ${it.data}")
                    it.data?.let {
                        if (it > 0) {
                            setFavouriteAnimation()
                        }
                    }
                }
                Status.ERROR -> {
                    Timber.d("error: ${it.message}")
                }
            }
        }

        databaseViewModel.isFavouriteSongResult.observe(viewLifecycleOwner) { it ->
            when (it.status) {
                Status.LOADING -> {
                    Timber.d("loading...")
                }
                Status.SUCCESS -> {
                    Timber.d("success: ${it.data}")

                    it.data?.let {
                        if (it) {
                            setFavouriteAnimation()
                        } else {
                            unFavouriteAnimation()
                        }
                    }

                }
                Status.ERROR -> {
                    Timber.d("error: ${it.message}")
                }
            }
        }


        databaseViewModel.removeFavouriteSongResult.observe(viewLifecycleOwner) { it ->
            when (it.status) {
                Status.LOADING -> {
                    Timber.d("loading...")
                }
                Status.SUCCESS -> {
                    Timber.d("success: ${it.data}")
                    it.data?.let {
                        unFavouriteAnimation()
                    }
                }
                Status.ERROR -> {
                    Timber.d("error: ${it.message}")
                }
            }
        }
    }

    private fun unFavouriteAnimation() {
        favoriteView.isActivated = false
        favoriteView.playAnimation();
        favoriteView.cancelAnimation();
    }

    private fun setFavouriteAnimation() {
        favoriteView.isActivated = true
        favoriteView.playAnimation()
    }
}