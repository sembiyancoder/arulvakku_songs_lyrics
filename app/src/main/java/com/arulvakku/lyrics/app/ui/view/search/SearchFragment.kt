package com.arulvakku.lyrics.app.ui.view.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arulvakku.lyrics.app.databinding.SearchFragmentBinding
import com.arulvakku.lyrics.app.ui.listeners.CellClickListenerSongs
import com.arulvakku.lyrics.app.ui.view.SongDetailsActivity
import com.arulvakku.lyrics.app.ui.view.home.song.SongModel
import com.arulvakku.lyrics.app.ui.view.search.adapter.SearchSongsAdapter
import com.arulvakku.lyrics.app.ui.viewmodels.DatabaseViewModel
import com.arulvakku.lyrics.app.utilities.SongsSingleton
import com.arulvakku.lyrics.app.utilities.Status
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SearchFragment : Fragment(), CellClickListenerSongs {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: SearchFragmentBinding
    private val databaseViewModel: DatabaseViewModel by viewModels()
    lateinit var songSearchAdapter: SearchSongsAdapter
    lateinit var songsList: List<SongModel>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        subscribe()

        binding.editSearchText.addTextChangedListener(afterTextChanged = {

            if (it.toString().length > 2) {
                songSearchAdapter.filter.filter(it.toString().trim())
            } else {
                setAdapter()
            }
        })
    }

    private fun subscribe() {
        databaseViewModel.getSongs()
        databaseViewModel.songsResult.observe(viewLifecycleOwner) { it ->
            when (it.status) {
                Status.LOADING -> {
                    Timber.d("loading...")
                }
                Status.SUCCESS -> {
                    Timber.d("success: ${it.data}")
                    songsList = it.data ?: emptyList()
                    setAdapter()
                }
                Status.ERROR -> {
                    Timber.d("error: ${it.message}")
                }
            }
        }
    }

    private fun setAdapter() {
        SongsSingleton.setSongs(songsList as ArrayList<SongModel>)
        songSearchAdapter = SearchSongsAdapter(
            requireActivity(),
            songsList,
            this@SearchFragment
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = songSearchAdapter
        songSearchAdapter.notifyDataSetChanged()
    }
    override fun onSongCellClickListener(item: SongModel,position: Int) {
        val intent = Intent(context, SongDetailsActivity::class.java)
        val bundle = Bundle().apply {
            putSerializable("song", item)
        }
        bundle.putInt("pos", position)
        intent.putExtras(bundle)
        startActivity(intent)
    }

}