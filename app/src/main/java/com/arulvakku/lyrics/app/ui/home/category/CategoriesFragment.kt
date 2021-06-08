package com.arulvakku.lyrics.app.ui.home.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.databinding.CategoriesFragmentBinding
import com.arulvakku.lyrics.app.ui.home.category.adapter.CategoryAdapter
import com.arulvakku.lyrics.app.ui.listeners.CellClickListener
import com.arulvakku.lyrics.app.ui.home.song.SongModel
import com.arulvakku.lyrics.app.viewmodel.DatabaseViewModel
import com.arulvakku.lyrics.app.utilities.Status
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CategoriesFragment : Fragment(), CellClickListener {

    companion object {
        fun newInstance() = CategoriesFragment()
    }


    private val databaseViewModel: DatabaseViewModel by viewModels()

    private var binding: CategoriesFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CategoriesFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribe()
    }

    private fun subscribe() {
        databaseViewModel.getSongCategories()
        databaseViewModel.categoriesResult.observe(viewLifecycleOwner) { it ->
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

    private fun setAdapter(list: List<SongCategoryModel>) {
        binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = activity?.let {
                CategoryAdapter(
                    it,
                    list,
                    this@CategoriesFragment
                )
            }
        }
    }


    override fun onCategoryItemClickListener(item: SongCategoryModel) {
        val bundle = Bundle().apply {
            putSerializable("categoriesresult", item)
        }
        findNavController().navigate(
            R.id.action_category_to_song_list,
            bundle
        )
    }


    override fun onResume() {
        super.onResume()

        Timber.d("CategoriesFragment")
    }
    override fun onSongCellClickListener(item: SongModel) {
        TODO("Not yet implemented")
    }
}