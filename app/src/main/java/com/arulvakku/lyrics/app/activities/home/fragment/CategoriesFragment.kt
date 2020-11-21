package com.arulvakku.lyrics.app.activities.home.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.activities.SongTitlesActivity
import com.arulvakku.lyrics.app.adapters.CategoryAdapter
import com.arulvakku.lyrics.app.data.Category
import com.arulvakku.lyrics.app.databinding.FragmentCatgoriesBinding
import com.arulvakku.lyrics.app.utilities.getSongCategories
import com.sembiyan.songs.app.listeners.CellClickListener


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CategoriesFragment : Fragment(), CellClickListener {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var categories: List<Category>

    var binding: FragmentCatgoriesBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catgories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCatgoriesBinding.bind(view)
        setAdapter();
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CategoriesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    fun setAdapter() {
        categories = activity?.let { getSongCategories(it) }!!
        binding?.recyclerView?.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = activity?.let { CategoryAdapter(it, categories, this@CategoriesFragment) }
        }
    }

    override fun onCellClickListener(item: String) {
        val intent = Intent(activity, SongTitlesActivity::class.java)
        intent.putExtra("category_name", item)
        startActivity(intent)
    }


}