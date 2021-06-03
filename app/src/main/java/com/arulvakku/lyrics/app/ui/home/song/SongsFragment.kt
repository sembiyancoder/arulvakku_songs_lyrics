package com.arulvakku.lyrics.app.ui.home.song

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arulvakku.lyrics.app.databinding.SongsFragmentBinding
import com.arulvakku.lyrics.app.ui.listeners.CellClickListener
import com.arulvakku.lyrics.app.ui.home.song.adapter.SongsAdapter
import com.arulvakku.lyrics.app.ui.home.category.SongCategoryModel
import com.arulvakku.lyrics.app.ui.SongDetailsActivity
import com.arulvakku.lyrics.app.viewmodel.DatabaseViewModel
import com.arulvakku.lyrics.app.utilities.Status
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SongsFragment : Fragment(), CellClickListener {

    companion object {
        fun newInstance() = SongsFragment()
    }

    private lateinit var viewModel: SongsViewModel
    private lateinit var binding: SongsFragmentBinding

    private val databaseViewModel: DatabaseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SongsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SongsViewModel::class.java)
        subscribe()
    }


    private fun setAdapter(list: List<SongModel>) {
        binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = activity?.let {
                SongsAdapter(
                    it,
                    list,
                    this@SongsFragment
                )
            }
        }
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

                    setAdapter(it.data ?: emptyList())
                }
                Status.ERROR -> {
                    Timber.d("error: ${it.message}")
                }
            }
        }


    }

    override fun onCategoryItemClickListener(item: SongCategoryModel) {
    }

    override fun onSongCellClickListener(item: SongModel) {
        val intent = Intent(context, SongDetailsActivity::class.java)
        val bundle = Bundle().apply {
            putSerializable("song", item)
        }
        intent.putExtras(bundle)
        startActivity(intent)
    }


}