package com.arulvakku.lyrics.app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arulvakku.lyrics.app.BR
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.data.Song
import com.arulvakku.lyrics.app.databinding.LayoutTitlesRowItemBinding
import com.sembiyan.songs.app.listeners.TitleCellClickListener

class TitlesAdapter(
    var context: Context,
    private val items: List<Song>,
    private val cellClickListener: TitleCellClickListener
) :
    RecyclerView.Adapter<TitlesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TitlesAdapter.ViewHolder {
        val binding: LayoutTitlesRowItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.layout_titles_row_item, parent, false
        )
        return TitlesAdapter.ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TitlesAdapter.ViewHolder, position: Int) {
        holder.bind(items.get(position))

        holder.itemView.setOnClickListener {
            cellClickListener.onTitleCellClickListener(
                items.get(position).category,
                items.get(position).title,
                items.get(position).song
            )
        }
    }

    // Creating ViewHolder
    class ViewHolder(val binding: LayoutTitlesRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Any) {
            binding.setVariable(
                BR.song,
                data
            ) //BR - generated class; BR.user - 'user' is variable name declared in layout
            binding.executePendingBindings()
        }
    }


}