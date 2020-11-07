package com.arulvakku.lyrics.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.arulvakku.lyrics.app.adapters.CategoryAdapter
import com.arulvakku.lyrics.app.data.Category
import com.arulvakku.lyrics.app.utilities.getJsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sembiyan.songs.app.listeners.CellClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CellClickListener {

    private lateinit var categories: List<Category>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //reading songs categories from the assets
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
        recyclerView?.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = CategoryAdapter(this@MainActivity, categories, this@MainActivity)
        }
    }

    /**
     * On category selected item call back
     */
    override fun onCellClickListener(item: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("category_name", item)
        startActivity(intent)
    }

}