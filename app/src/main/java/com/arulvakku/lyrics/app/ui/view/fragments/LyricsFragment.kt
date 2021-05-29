package com.arulvakku.lyrics.app.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arulvakku.lyrics.app.databinding.LyricsFragmentBinding
import com.arulvakku.lyrics.app.ui.viewmodels.LyricsViewModel

class LyricsFragment : Fragment() {

    companion object {
        fun newInstance() = LyricsFragment()
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
        viewModel = ViewModelProvider(this).get(LyricsViewModel::class.java)
    }

}