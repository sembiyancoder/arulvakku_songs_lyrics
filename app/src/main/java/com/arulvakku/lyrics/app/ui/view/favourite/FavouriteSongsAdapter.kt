package com.arulvakku.lyrics.app.ui.view.favourite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arulvakku.lyrics.app.databinding.LayoutFavouriteSongItemBinding
import com.arulvakku.lyrics.app.ui.view.home.song.cache.SongCacheEntity

class FavouriteSongsAdapter(fragment: FavouriteFragment) : RecyclerView.Adapter<FavouriteSongsAdapter.MyViewHolder>() {

    val onClick: OnClick = fragment
    val list = mutableListOf<SongCacheEntity>()

    fun update(list: List<SongCacheEntity>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): MyViewHolder {
        val view =
                LayoutFavouriteSongItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(list[position], position, onClick)
    }

    class MyViewHolder(binding: LayoutFavouriteSongItemBinding) :
            RecyclerView.ViewHolder(binding.root) {


        val view = binding
        @SuppressLint("SetTextI18n")
        fun bind(data: SongCacheEntity, position: Int, onClick: OnClick) {
            view.textViewNumber.text = "${(position + 1)}. "
            view.textViewSongTitle.text = data.sTitle
            view.textViewSongDesc.text = data.sSong

            view.imageViewFavourite.setOnClickListener {
                val id: Int = data.sSongId ?: 0
                onClick.onClick(id, position)
            }

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun remove(position: Int) {
        list.removeAt(position)
        notifyDataSetChanged()
    }

    interface OnClick {
        fun onClick(id: Int, position: Int)
    }
}