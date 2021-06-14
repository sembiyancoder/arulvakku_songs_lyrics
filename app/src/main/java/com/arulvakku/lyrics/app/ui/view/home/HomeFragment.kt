package com.arulvakku.lyrics.app.ui.view.home


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.data.model.GlobalInfo
import com.arulvakku.lyrics.app.databinding.HomeFragmentBinding
import com.arulvakku.lyrics.app.ui.listeners.CellClickListenerCategory
import com.arulvakku.lyrics.app.ui.view.home.adapter.CategoryAdapter
import com.arulvakku.lyrics.app.ui.view.home.model.SongCategoryModel
import com.arulvakku.lyrics.app.ui.viewmodels.DataStoreViewModel
import com.arulvakku.lyrics.app.ui.viewmodels.DatabaseViewModel
import com.arulvakku.lyrics.app.utilities.NetworkHelper
import com.arulvakku.lyrics.app.utilities.Status
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class HomeFragment : Fragment(), CellClickListenerCategory {

    companion object {
        private const val TAG = "HomeFragment"
    }

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val databaseViewModel: DatabaseViewModel by viewModels()
    private val dataStoreViewModel: DataStoreViewModel by viewModels()
    
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

        if (NetworkHelper(requireContext()).isNetworkConnected()) {
            checkForUserInfo();
        } else {
            binding.layoutInfo.visibility = View.GONE
        }

        binding.imgClose.setOnClickListener {
            binding.layoutInfo.visibility = View.GONE
            dataStoreViewModel.setUserClosedGlobalInfoKey(true)
        }
    }

    private fun checkForUserInfo() {
        val database = Firebase.database
        val myRef = database.getReference("globalinfo")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val globalInfo = dataSnapshot.getValue(GlobalInfo::class.java)
                if (globalInfo != null) {
                    isClosed(globalInfo)
                    checkForChange(globalInfo)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.ui_menu, menu)
    }

    private fun checkForChange(globalInfo: GlobalInfo) {
        dataStoreViewModel.userMessage.observe(this) {
            if (it == globalInfo.message) {
                binding.layoutInfo.visibility = View.GONE
            } else {
                binding.layoutInfo.visibility = View.VISIBLE
                binding.txtInfo.text = globalInfo.message
                binding.txtInfo.setTextColor(Color.parseColor(globalInfo.textColor))
                binding.layoutInfo.setBackgroundColor(Color.parseColor(globalInfo.bgColor))

                //dataStoreViewModel.setUserClosedGlobalInfoKey(false)
                //dataStoreViewModel.setUserGlobalMessage(globalInfo.message)
            }
        }


    }

    private fun isClosed(globalInfo: GlobalInfo) {
        dataStoreViewModel.userClosedGlobalInfoKey.observe(this) {
            if (it == true) {
                binding.layoutInfo.visibility = View.GONE
            } else {
                if (globalInfo != null) {
                    if (globalInfo.isShow) {
                        binding.txtInfo.text = globalInfo.message
                        binding.txtInfo.setTextColor(Color.parseColor(globalInfo.textColor))
                        binding.layoutInfo.setBackgroundColor(Color.parseColor(globalInfo.bgColor))
                        binding.layoutInfo.visibility = View.VISIBLE
                        dataStoreViewModel.setUserGlobalMessage(globalInfo.message)
                    } else {
                        binding.layoutInfo.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        return when (item.itemId) {
            R.id.action_share -> {
                val shareMsg = getString(
                    R.string.share_app,
                    "Download Android App",
                    "Arulvakku is the first in the internet media that serves the Tamil speaking Christian community across the world, by giving Holy Bible online, Online Radio and Online Bible Study. Easy Bible reading and searching, receiving daily emails with daily liturgical readings and reflections both in English and Tamil, Rosaries and prayers, videos and hymns are some of the resources available online at arulvakku.com for Tamil Christians."
                )

                val intent = ShareCompat.IntentBuilder.from(requireActivity())
                    .setType("text/plain")
                    .setText(shareMsg)
                    .intent

                if (intent.resolveActivity(requireActivity().packageManager) != null) {
                    startActivity(intent)
                }
                true
            }
            /*R.id.action_about -> {
                findNavController().navigate(R.id.action_notesFragment_to_aboutFragment)
                true
            }*/
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setUIMode(item: MenuItem, isChecked: Boolean) {
        /*if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            // dataStoreViewModel.setSavedKey(true)
            item.setIcon(R.drawable.ic_night)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            // dataStoreViewModel.setSavedKey(false)
            item.setIcon(R.drawable.ic_day)
        }*/
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
        binding.recyclerView.apply {
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


    override fun onCategoryItemClickListener(item: SongCategoryModel, position: Int) {
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
}