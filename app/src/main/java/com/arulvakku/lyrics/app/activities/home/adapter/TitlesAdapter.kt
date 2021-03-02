package com.arulvakku.lyrics.app.activities.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.arulvakku.lyrics.app.data.Song
import com.arulvakku.lyrics.app.databinding.LayoutLyrcisTitleSubtitleRowItemBinding
import com.sembiyan.songs.app.listeners.RecyclerOnRowItemClickListener
import java.util.*
import kotlin.collections.ArrayList

class TitlesAdapter(
    var context: Context,
    private val items: List<Song>,
    private val cellClickListener: RecyclerOnRowItemClickListener, var isFromCategory: Boolean
) :
    RecyclerView.Adapter<TitlesAdapter.ViewHolder>(), Filterable {

    private var titleFilterList: List<Song>

    init {
        titleFilterList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    override fun getItemCount(): Int = titleFilterList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = titleFilterList[position]
        holder.bind(song.sTitle, position, song.sCategory, isFromCategory)

        holder.itemView.setOnClickListener {
            cellClickListener.onItemRowListener(position, titleFilterList[position])
        }
    }


    class ViewHolder private constructor(private val binding: LayoutLyrcisTitleSubtitleRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(name: String, count: Int, category: String, isFromCategory: Boolean) {
            binding.txtCategoryTitle.text = name.trim()
            binding.textView3.text = category.trim()
            val rowCount = count + 1
            binding.txtCount.text = "$rowCount. "

            if (!isFromCategory) binding.textView3.visibility = View.GONE else {
                binding.textView3.visibility = View.VISIBLE
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    LayoutLyrcisTitleSubtitleRowItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    titleFilterList = items
                } else {
                    val resultList = ArrayList<Song>()
                    for (row in items) {
                        if (row.sTitle.toLowerCase(Locale.ROOT)
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
                titleFilterList = results?.values as List<Song>
                notifyDataSetChanged()
            }

        }
    }


}