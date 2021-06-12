package com.arulvakku.lyrics.app.ui.view.favourite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arulvakku.lyrics.app.databinding.LayoutCategoryRowItemBinding
import com.arulvakku.lyrics.app.databinding.LayoutFavouriteSongItemBinding
import com.arulvakku.lyrics.app.ui.listeners.CellClickListenerSongs
import com.arulvakku.lyrics.app.ui.view.favourite.cache.CacheMapper
import com.arulvakku.lyrics.app.databinding.LayoutSongRowItemBinding
import com.arulvakku.lyrics.app.ui.view.home.song.cache.SongCacheEntity
import javax.inject.Inject
import javax.inject.Singleton

class FavouriteSongsAdapter (fragment: FavouriteFragment, val cacheMapper: CacheMapper) :
    RecyclerView.Adapter<FavouriteSongsAdapter.MyViewHolder>() {

    val onClick: OnClick = fragment
    val list = mutableListOf<SongCacheEntity>()

    private val clickListener: CellClickListenerSongs = fragment

    fun update(list: List<SongCacheEntity>) {}
    fun update(list: List<SongCacheEntity>,textView: TextView) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()

        textView.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view =
                LayoutSongRowItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(list[position], position, onClick)

        holder.itemView.setOnClickListener {
            clickListener.onSongCellClickListener(cacheMapper.mapToEntity(list[position]), position)
        }
    }

    class MyViewHolder(binding: LayoutSongRowItemBinding) :
            RecyclerView.ViewHolder(binding.root) {


        val view = binding

        @SuppressLint("SetTextI18n")
        fun bind(data: SongCacheEntity, position: Int, onClick: OnClick) {
            view.txtCount.text = "${(position + 1)}. "
            view.txtCategoryTitle.text = data.sTitle
            view.textCategoryName.text = data.sCategory

            view.imageArrow.setOnClickListener {
                onClick.onClick(data, position)
            }

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun remove(position: Int,textView: TextView) {
        list.removeAt(position)
        notifyDataSetChanged()

        textView.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
    }

    interface OnClick {
        fun onClick(data: SongCacheEntity, position: Int)
    }
}