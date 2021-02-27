package com.arulvakku.lyrics.app.activities.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arulvakku.lyrics.app.data.Category
import com.arulvakku.lyrics.app.databinding.LayoutLyrcisTitleRowItemBinding
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
        holder.bind(category.title, category.count, category.color_code, position)
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(categoryItems.get(position).title)
        }
    }


    class ViewHolder private constructor(private val binding: LayoutLyrcisTitleRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(name: String, count: String, colorCode: String, position: Int) {
            binding.txtCategoryTitle.text = name
            val rowCount = position + 1
            binding.txtCount.text = "$rowCount."
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutLyrcisTitleRowItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}