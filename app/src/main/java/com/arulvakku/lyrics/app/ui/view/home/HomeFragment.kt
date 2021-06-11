package com.arulvakku.lyrics.app.ui.view.home


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.databinding.HomeFragmentBinding
import com.arulvakku.lyrics.app.ui.listeners.CellClickListener
import com.arulvakku.lyrics.app.ui.view.home.model.SongCategoryModel
import com.arulvakku.lyrics.app.ui.view.home.adapter.CategoryAdapter
import com.arulvakku.lyrics.app.ui.view.home.song.SongModel
import com.arulvakku.lyrics.app.ui.viewmodels.DataStoreViewModel
import com.arulvakku.lyrics.app.ui.viewmodels.DatabaseViewModel
import com.arulvakku.lyrics.app.utilities.Status
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment(), CellClickListener {
    private var _binding: HomeFragmentBinding? = null

    private val binding get() = _binding!!

    private val dataStoreViewModel: DataStoreViewModel by viewModels()
    private val databaseViewModel: DatabaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
        subscribe()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.ui_menu, menu)
        // Set the item state
        /*dataStoreViewModel.savedKey.observe(this) {
            if (it == true) {
                *//*val item = menu.findItem(R.id.action_night_mode)
                item.isChecked = it
                setUIMode(item, it)*//*
            }
        }*/
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
           // dataStoreViewModel.setSavedKey(true)
            item.setIcon(R.drawable.ic_night)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
           // dataStoreViewModel.setSavedKey(false)
            item.setIcon(R.drawable.ic_day)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
                    this@HomeFragment
                )
            }
        }
    }


    override fun onCategoryItemClickListener(item: SongCategoryModel) {
        val bundle = Bundle().apply {
            putSerializable("categoriesresult", item)
        }
        findNavController().navigate(
            R.id.navigation_songs_list,
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

    override fun onSongCellClickListenerWithPosition(item: SongModel, position: Int) {
        TODO("Not yet implemented")
    }
}