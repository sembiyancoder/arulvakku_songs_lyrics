package com.arulvakku.lyrics.app.ui.view.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arulvakku.lyrics.app.BuildConfig
import com.arulvakku.lyrics.app.databinding.SettingFragmentBinding
import com.arulvakku.lyrics.app.ui.listeners.CellClickListenerMore
import com.arulvakku.lyrics.app.ui.view.more.adapter.MoreListItemAdapter
import com.arulvakku.lyrics.app.ui.view.more.model.MoreData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreFragment : Fragment(), CellClickListenerMore {

    companion object {
        fun newInstance() = MoreFragment()
    }

    private var _binding: SettingFragmentBinding? = null
    private val binding get() = _binding!!


    private lateinit var viewModel: MoreViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SettingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MoreViewModel::class.java)
        context?.let { viewModel.readDataFromAssets(it) }
        setAdapter(viewModel.data)

        setVersionDetails()
    }

    fun setVersionDetails() {
        val versionName = BuildConfig.VERSION_NAME
        val versionCode = BuildConfig.VERSION_CODE
        binding.txtAppVersion.text = "App Version:  $versionName ($versionCode)"
    }

    private fun setAdapter(list: MutableList<MoreData>) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = activity?.let {
                MoreListItemAdapter(
                    it,
                    list,
                    this@MoreFragment
                )
            }
        }
    }

    override fun onMoreItemClickListener(item: MoreData, position: Int) {

    }

}