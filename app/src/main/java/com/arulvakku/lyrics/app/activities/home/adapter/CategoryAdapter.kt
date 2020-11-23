package com.arulvakku.lyrics.app.activities.home.adapter

import android.content.Context
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.data.Category
import com.arulvakku.lyrics.app.databinding.LayoutCategoryRowItemBinding
import com.arulvakku.lyrics.app.utilities.Constants
import com.arulvakku.lyrics.app.utilities.Prefs
import com.sembiyan.songs.app.listeners.CellClickListener

class CategoryAdapter(
        var context: Context,
        private val categoryItems: List<Category>,
        private val cellClickListener: CellClickListener
) :
        RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int = categoryItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categoryItems[position]
        holder.bind(category.title, category.count, category.color_code)
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(categoryItems.get(position).title)
        }
    }


    class ViewHolder private constructor(private val binding: LayoutCategoryRowItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(name: String, count: String, colorCode: String) {
            binding.txtCategoryTitle.text = name
            val font = Prefs.getString(Constants.SP_KEYS.FONT_SIZE,"Medium")
            when {
                font.equals("Small") -> {
                    binding.txtCategoryTitle.setTextSize(COMPLEX_UNIT_SP,14f)
                }
                font.equals("Large") -> {
                    binding.txtCategoryTitle.setTextSize(COMPLEX_UNIT_SP,24f)
                }
                else -> {
                    binding.txtCategoryTitle.setTextSize(COMPLEX_UNIT_SP,17f)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutCategoryRowItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}