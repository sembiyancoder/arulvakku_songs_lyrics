package com.arulvakku.lyrics.app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.data.Song
import com.sembiyan.songs.app.listeners.TitleCellClickListener
import java.util.*
import kotlin.collections.ArrayList

class TitlesAdapter(
    var context: Context,
    private val items: List<Song>,
    private val cellClickListener: TitleCellClickListener
) :
    RecyclerView.Adapter<TitlesAdapter.ViewHolder>(), Filterable {

    private var countryFilterList: List<Song>

    init {
        countryFilterList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitlesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_titles_row_item, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int = countryFilterList.size

    override fun onBindViewHolder(holder: TitlesAdapter.ViewHolder, position: Int) {
        holder.bindItems(countryFilterList.get(position))

        holder.itemView.setOnClickListener {
            cellClickListener.onTitleCellClickListener(
                countryFilterList.get(position).category,
                countryFilterList.get(position).title,
                countryFilterList.get(position).song
            )
        }
    }

    // Creating ViewHolder
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(song: Song) {
            val textViewName = itemView.findViewById(R.id.txt_category_title) as TextView
            textViewName.text = song.title.trim()
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    countryFilterList = items
                } else {
                    val resultList = ArrayList<Song>()
                    for (row in items) {
                        if (row.title.substring(0, 1).toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    countryFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = countryFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countryFilterList = results?.values as List<Song>
                notifyDataSetChanged()
            }

        }
    }


}