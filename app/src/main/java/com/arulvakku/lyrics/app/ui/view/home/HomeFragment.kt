package com.arulvakku.lyrics.app.ui.view.home


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.databinding.HomeFragmentBinding
import com.arulvakku.lyrics.app.ui.adapters.ViewPagerAdapter
import com.arulvakku.lyrics.app.ui.view.home.category.CategoriesFragment
import com.arulvakku.lyrics.app.ui.view.home.song.SongsFragment
import com.arulvakku.lyrics.app.ui.viewmodels.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: HomeFragmentBinding? = null


    private val binding get() = _binding!!
    private val dataStoreViewModel: DataStoreViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(CategoriesFragment(), "பிரிவுகள்")
        adapter.addFragment(SongsFragment(), "பாடல்கள்")
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        findNavController().navigate(R.id.navigation_category)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.ui_menu, menu)
        // Set the item state
        dataStoreViewModel.savedKey.observe(this) {
            if (it == true) {
                val item = menu.findItem(R.id.action_night_mode)
                item.isChecked = it
                setUIMode(item, it)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        return when (item.itemId) {
            R.id.action_night_mode -> {
                item.isChecked = !item.isChecked
                setUIMode(item, item.isChecked)
                true
            }

            R.id.action_about -> {
                findNavController().navigate(R.id.action_notesFragment_to_aboutFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setUIMode(item: MenuItem, isChecked: Boolean) {
        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            dataStoreViewModel.setSavedKey(true)
            item.setIcon(R.drawable.ic_night)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            dataStoreViewModel.setSavedKey(false)
            item.setIcon(R.drawable.ic_day)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}