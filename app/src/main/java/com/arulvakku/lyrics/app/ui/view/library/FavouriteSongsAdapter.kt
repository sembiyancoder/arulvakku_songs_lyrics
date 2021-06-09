package com.arulvakku.lyrics.app.ui.view.library

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arulvakku.lyrics.app.databinding.LayoutFavouriteSongItemBinding
import com.arulvakku.lyrics.app.ui.view.home.song.cache.SongCacheEntity

class FavouriteSongsAdapter : RecyclerView.Adapter<FavouriteSongsAdapter.MyViewHolder>() {

    val list = mutableListOf<SongCacheEntity>()

    fun update(list: List<SongCacheEntity>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavouriteSongsAdapter.MyViewHolder {
        val view =
            LayoutFavouriteSongItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavouriteSongsAdapter.MyViewHolder, position: Int) {

        holder.bind(list[position], position)
    }

    class MyViewHolder(binding: LayoutFavouriteSongItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val view = binding
        fun bind(data: SongCacheEntity, position: Int) {
            view.textViewNumber.text = "${(position + 1)}. "
            view.textViewSongTitle.text = data.sTitle
            view.textViewSongDesc.text = data.sSong

        }

    }

    override fun getItemCount(): Int {
      return list.size
    }
}