package com.arulvakku.lyrics.app.activities.home.setting

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arulvakku.lyrics.app.R

class Settingsragment : Fragment() {

    companion object {
        fun newInstance() = Settingsragment()
    }

    private lateinit var viewModel: SettingsragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settingsragment_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SettingsragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}