package com.arulvakku.lyrics.app

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.arulvakku.lyrics.app.activities.BaseActivity
import com.arulvakku.lyrics.app.activities.SongTitlesActivity
import com.arulvakku.lyrics.app.adapters.CategoryAdapter
import com.arulvakku.lyrics.app.data.Category
import com.arulvakku.lyrics.app.databinding.ActivityMainBinding
import com.arulvakku.lyrics.app.utilities.getJsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sembiyan.songs.app.listeners.CellClickListener

class MainActivity : BaseActivity(), CellClickListener {

    private lateinit var categories: List<Category>

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareCategory()
    }


    /**
     * preparing json data from categories
     */
    private fun prepareCategory() {
        val jsonFileString = getJsonDataFromAsset(applicationContext, "songscategories.json")
        val gson = Gson()
        val listCategory = object : TypeToken<List<Category>>() {}.type
        categories = gson.fromJson(jsonFileString, listCategory)
        setCategoriesAdapter()
    }

    /**
     * Setting category adapter
     */
    fun setCategoriesAdapter() {
        binding.recyclerView?.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = CategoryAdapter(this@MainActivity, categories, this@MainActivity)
        }
    }

    /**
     * On category selected item call back
     */
    override fun onCellClickListener(item: String) {
        val intent = Intent(this, SongTitlesActivity::class.java)
        intent.putExtra("category_name", item)
        startActivity(intent)
    }

}