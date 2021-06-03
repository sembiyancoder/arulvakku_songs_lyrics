package com.arulvakku.lyrics.app.ui.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.arulvakku.lyrics.app.databinding.LayoutSongRowItemBinding
import com.arulvakku.lyrics.app.ui.listeners.CellClickListener
import com.arulvakku.lyrics.app.ui.home.song.SongModel
import java.util.*
import kotlin.collections.ArrayList


class SearchSongsAdapter(
    var context: Context,
    private val categoryItems: List<SongModel>,
    private val cellClickListener: CellClickListener
) :
    RecyclerView.Adapter<SearchSongsAdapter.ViewHolder>(), Filterable {

    private var titleFilterList: List<SongModel>

    init {
        titleFilterList = categoryItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int = titleFilterList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = titleFilterList[position]
        holder.bind(category, position)
        holder.itemView.setOnClickListener {
            cellClickListener.onSongCellClickListener(titleFilterList.get(position))
        }
    }


    class ViewHolder private constructor(private val binding: LayoutSongRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(songResult: SongModel, position: Int) {
            binding.txtCategoryTitle.text = songResult.sTitle
            val rowCount = position + 1
            binding.txtCount.text = "$rowCount."
            binding.textCategoryName.text = songResult.sCategory
            binding.textCategoryName.visibility = View.VISIBLE
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutSongRowItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    titleFilterList = categoryItems
                } else {
                    val resultList = ArrayList<SongModel>()
                    for (row in categoryItems) {
                        if (row.sTitle!!.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    titleFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = titleFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                titleFilterList = results?.values as List<SongModel>
                notifyDataSetChanged()
            }

        }
    }
}