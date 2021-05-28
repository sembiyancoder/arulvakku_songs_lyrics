package com.arulvakku.lyrics.app.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.data.model.CategoriesResult
import com.arulvakku.lyrics.app.data.model.SongResult
import com.arulvakku.lyrics.app.databinding.CategoriesFragmentBinding
import com.arulvakku.lyrics.app.ui.adapters.CategoryAdapter
import com.arulvakku.lyrics.app.ui.adapters.CellClickListener
import com.arulvakku.lyrics.app.ui.viewmodels.CategoriesViewModel

class CategoriesFragment : Fragment(), CellClickListener {

    companion object {
        fun newInstance() = CategoriesFragment()
    }

    private lateinit var viewModel: CategoriesViewModel
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
        viewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
        setAdapter()
    }

    private fun setAdapter() {
        binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = activity?.let {
                CategoryAdapter(
                    it,
                    viewModel.songCategoriesResult.Result,
                    this@CategoriesFragment
                )
            }
        }
    }


    override fun onCellClickListener(item: CategoriesResult) {
        val bundle = Bundle().apply {
            putSerializable("article", item.sCategoryId)
        }
        findNavController().navigate(
            R.id.action_category_to_song_list,
            bundle
        )
    }

    override fun onSongCellClickListener(item: SongResult) {

    }

}