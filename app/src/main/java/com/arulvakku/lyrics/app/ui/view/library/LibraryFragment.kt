package com.arulvakku.lyrics.app.ui.view.library

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.databinding.LibraryFragmentBinding
import com.arulvakku.lyrics.app.ui.view.home.song.SongsViewModel
import com.arulvakku.lyrics.app.ui.viewmodels.DatabaseViewModel
import com.arulvakku.lyrics.app.utilities.Status
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class LibraryFragment : Fragment() {

    private var _binding: LibraryFragmentBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = LibraryFragment()
    }

    private val databaseViewModel: DatabaseViewModel by viewModels()

    private val adapter = FavouriteSongsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LibraryFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                        adapter.update(it.songs ?: emptyList())
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
}