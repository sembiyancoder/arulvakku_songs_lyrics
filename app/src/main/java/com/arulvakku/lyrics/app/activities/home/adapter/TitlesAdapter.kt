package com.arulvakku.lyrics.app.activities.home.adapter

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.arulvakku.lyrics.app.data.Song
import com.arulvakku.lyrics.app.databinding.LayoutTitlesRowItemBinding
import com.arulvakku.lyrics.app.utilities.Constants
import com.arulvakku.lyrics.app.utilities.Prefs
import com.sembiyan.songs.app.listeners.TitleCellClickListener
import java.util.*
import kotlin.collections.ArrayList

class TitlesAdapter(
    var context: Context,
    private val items: List<Song>,
    private val cellClickListener: TitleCellClickListener
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
        holder.bind(song.title, position)
        holder.itemView.setOnClickListener {
            cellClickListener.onTitleCellClickListener(
                titleFilterList.get(position).category,
                titleFilterList.get(position).title,
                titleFilterList.get(position).song
            )
        }
    }


    class ViewHolder private constructor(private val binding: LayoutTitlesRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(name: String, count: Int) {
            binding.txtSongTitle.text = name.trim()
            val rowCount = count + 1
            binding.txtCount.text = "$rowCount. "
            val font = Prefs.getString(Constants.SP_KEYS.FONT_SIZE,"Medium")
            when {
                font.equals("Small") -> {
                    binding.txtSongTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,14f)
                    binding.txtCount.setTextSize(TypedValue.COMPLEX_UNIT_SP,14f)
                }
                font.equals("Large") -> {
                    binding.txtSongTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,24f)
                    binding.txtCount.setTextSize(TypedValue.COMPLEX_UNIT_SP,24f)
                }
                else -> {
                    binding.txtSongTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,17f)
                    binding.txtCount.setTextSize(TypedValue.COMPLEX_UNIT_SP,17f)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutTitlesRowItemBinding.inflate(layoutInflater, parent, false)
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
                        if (row.title.toLowerCase(Locale.ROOT)
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