package com.arulvakku.lyrics.app.ui.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arulvakku.lyrics.app.data.model.CategoriesResult
import com.arulvakku.lyrics.app.data.model.SongResult
import com.arulvakku.lyrics.app.databinding.SongsFragmentBinding
import com.arulvakku.lyrics.app.ui.adapters.CellClickListener
import com.arulvakku.lyrics.app.ui.adapters.SongsAdapter
import com.arulvakku.lyrics.app.ui.view.MainActivity
import com.arulvakku.lyrics.app.ui.view.SongDetailsActivity
import com.arulvakku.lyrics.app.ui.viewmodels.SongsViewModel

class SongsFragment : Fragment(), CellClickListener {

    companion object {
        fun newInstance() = SongsFragment()
    }

    private lateinit var viewModel: SongsViewModel
    private lateinit var binding: SongsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SongsFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SongsViewModel::class.java)
        setAdapter()
    }


    private fun setAdapter() {
        binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = activity?.let {
                SongsAdapter(
                    it,
                    viewModel.songsResult.Result,
                    this@SongsFragment
                )
            }
        }
    }

    override fun onCellClickListener(item: CategoriesResult) {

    }

    override fun onSongCellClickListener(item: SongResult) {
        val intent = Intent(context, SongDetailsActivity::class.java)
        startActivity(intent)
    }

}