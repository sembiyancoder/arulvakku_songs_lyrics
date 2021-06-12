package com.arulvakku.lyrics.app.ui.view.favourite

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.databinding.LibraryFragmentBinding
import com.arulvakku.lyrics.app.ui.listeners.CellClickListenerSongs
import com.arulvakku.lyrics.app.ui.view.SongDetailsActivity
import com.arulvakku.lyrics.app.ui.view.favourite.cache.CacheMapper
import com.arulvakku.lyrics.app.ui.view.home.song.SongModel
import com.arulvakku.lyrics.app.ui.view.home.song.cache.SongCacheEntity
import com.arulvakku.lyrics.app.ui.viewmodels.DatabaseViewModel
import com.arulvakku.lyrics.app.utilities.SongsSingleton
import com.arulvakku.lyrics.app.utilities.Status
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class FavouriteFragment : Fragment(), FavouriteSongsAdapter.OnClick, CellClickListenerSongs {

    @Inject
    lateinit var cacheMapper: CacheMapper
    private var _binding: LibraryFragmentBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!


    private var position = 0

    companion object {
        fun newInstance() = FavouriteFragment()
    }

    private val databaseViewModel: DatabaseViewModel by viewModels()

    private lateinit var adapter: FavouriteSongsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LibraryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FavouriteSongsAdapter(this, cacheMapper)
        binding.recyclerView.adapter = adapter
        subscribe()

    }

    private fun subscribe() {
        databaseViewModel.getFavouriteSongs()
        databaseViewModel.getFavouriteSongsResult.observe(viewLifecycleOwner) { it ->
            when (it.status) {
                Status.LOADING -> {
                    Timber.d("loading...")
                }
                Status.SUCCESS -> {
                    Timber.d("success: ${it.data}")
                    it.data?.let {
                        adapter.update(it.songs ?: emptyList(), binding.textViewNoDataFound)
                    }
                    SongsSingleton.setSongs(cacheMapper.mapToEntityList(it.data?.songs!!) as ArrayList<SongModel>)

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
                        if (it > 0) adapter.remove(position, binding.textViewNoDataFound)
                    }
                }
                Status.ERROR -> {
                    Timber.d("error: ${it.message}")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onClick(id: Int, position: Int) {
//        databaseViewModel.removeFavouriteSong(id)
        this.position = position
        showNotification(id)
    }

    private fun showNotification(songId: Int) {
        val builder1: AlertDialog.Builder = AlertDialog.Builder(requireContext(), R.style.MyDialog)
        builder1.setTitle(R.string.app_name)
        builder1.setMessage("உங்களுக்கு பிடித்த பட்டியலிலிருந்து இந்த பாடலை நீக்க விரும்புகிறீர்களா?")
        builder1.setCancelable(true)

        builder1.setPositiveButton(
            "ஆம்",
            DialogInterface.OnClickListener { dialog, id ->
                databaseViewModel.removeFavouriteSong(songId)
                dialog.cancel()
            })

        builder1.setNegativeButton(
            "இல்லை",
            DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })

        val alert11: AlertDialog = builder1.create()
        alert11.show()
    }

    override fun onSongCellClickListener(item: SongModel, position: Int) {
        val intent = Intent(context, SongDetailsActivity::class.java)
        val bundle = Bundle().apply {
            putSerializable("song", item)
        }
        bundle.putInt("pos", position)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun onClick(data: SongCacheEntity, position: Int) {

    }
}