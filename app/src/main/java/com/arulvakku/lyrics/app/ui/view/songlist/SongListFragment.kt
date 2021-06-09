package com.arulvakku.lyrics.app.ui.view.songlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.arulvakku.lyrics.app.databinding.SongListFragmentBinding
import com.arulvakku.lyrics.app.ui.view.SongDetailsActivity
import com.arulvakku.lyrics.app.ui.view.home.category.SongCategoryModel
import com.arulvakku.lyrics.app.ui.view.home.song.SongModel
import com.arulvakku.lyrics.app.ui.view.songlist.adapter.SongsAdapter
import com.arulvakku.lyrics.app.utilities.Status
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class SongListFragment : Fragment(), CellClickListener {

    companion object {
        fun newInstance() = SongListFragment()
    }

    private lateinit var binding: SongListFragmentBinding
    private lateinit var viewModel: SongListViewModel

    val args: SongListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SongListFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SongListViewModel::class.java)
        val result = args.categoriesresult
        subscribe(result.sCategoryId)
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = result.sCategory
        Timber.d("success: ${result}")
    }

    private fun subscribe(categoryId: Int) {
        viewModel.getSongsListByCategory(categoryId);
        viewModel.songsResult.observe(viewLifecycleOwner) { it ->
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


    private fun setAdapter(list: List<SongModel>) {
        binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = activity?.let {
                SongsAdapter(
                    it,
                    list,
                    this@SongListFragment
                )
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