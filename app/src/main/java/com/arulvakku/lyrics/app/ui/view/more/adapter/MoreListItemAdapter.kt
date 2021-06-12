package com.arulvakku.lyrics.app.ui.view.more.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arulvakku.lyrics.app.databinding.LayoutMoreRowItemBinding
import com.arulvakku.lyrics.app.ui.listeners.CellClickListenerMore
import com.arulvakku.lyrics.app.ui.view.more.model.MoreData


class MoreListItemAdapter(
    var context: Context,
    private val categoryItems: MutableList<MoreData>,
    private val cellClickListenerMore: CellClickListenerMore
) :
    RecyclerView.Adapter<MoreListItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int = categoryItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categoryItems[position]
        holder.bind(category, position)
        holder.itemView.setOnClickListener {
            cellClickListenerMore.onMoreItemClickListener(
                categoryItems.get(position),
                position
            )
        }
    }

    class ViewHolder private constructor(private val binding: LayoutMoreRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(moreData: MoreData, position: Int) {
            binding.txtLabel.text = moreData.title
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutMoreRowItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}