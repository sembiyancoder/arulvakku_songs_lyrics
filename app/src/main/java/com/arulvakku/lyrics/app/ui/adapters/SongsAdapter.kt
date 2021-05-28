package com.arulvakku.lyrics.app.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arulvakku.lyrics.app.data.model.CategoriesResult
import com.arulvakku.lyrics.app.data.model.SongResult
import com.arulvakku.lyrics.app.databinding.LayoutCategoryRowItemBinding


class SongsAdapter(
    var context: Context,
    private val categoryItems: List<SongResult>,
    private val cellClickListener: CellClickListener
) :
    RecyclerView.Adapter<SongsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int = categoryItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categoryItems[position]
        holder.bind(category, position)
        holder.itemView.setOnClickListener {
            cellClickListener.onSongCellClickListener(categoryItems.get(position))
        }
    }


    class ViewHolder private constructor(private val binding: LayoutCategoryRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(songResult: SongResult, position: Int) {
            binding.txtCategoryTitle.text = songResult.sTitle
            val rowCount = position + 1
            binding.txtCount.text = "$rowCount."
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