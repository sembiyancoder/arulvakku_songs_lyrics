package com.arulvakku.lyrics.app.adapters

import android.content.Context
import android.graphics.Color
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.activities.category.SongTitlesActivity
import com.sembiyan.songs.app.listeners.CellFilterClickListener
import kotlinx.android.synthetic.main.layout_filter_row_item.view.*


class TitlesFilterAdapter(
    var context: Context,
    private val items: List<String>,
    private val cellClickListener: CellFilterClickListener
) :
    RecyclerView.Adapter<TitlesFilterAdapter.ViewHolder>() {

    val selectedItems = SparseBooleanArray()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TitlesFilterAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_filter_row_item, parent, false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TitlesFilterAdapter.ViewHolder, position: Int) {
        holder.bindItems(items.get(position))
        holder.itemView.setOnClickListener {
            cellClickListener.onFilterCellClickListener(items.get(position), position)
        }

        if (SongTitlesActivity.pos != -1 && SongTitlesActivity.pos == position) {
            holder.itemView.tempLayout.setBackgroundDrawable(context.resources.getDrawable(R.drawable.filtered_item_circle))
            holder.itemView.txt_category_title.setTextColor(Color.WHITE)
        } else {
            holder.itemView.tempLayout.setBackgroundDrawable(context.resources.getDrawable(R.drawable.filter_item_circle))
            holder.itemView.txt_category_title.setTextColor(Color.BLACK)
        }
    }

    // Creating ViewHolder
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(title: String) {
            val textViewName = itemView.findViewById(R.id.txt_category_title) as TextView
            textViewName.text = title.trim()
        }
    }
}