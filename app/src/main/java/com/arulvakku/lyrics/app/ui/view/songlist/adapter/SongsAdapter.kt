package  com.arulvakku.lyrics.app.ui.view.songlist.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arulvakku.lyrics.app.databinding.LayoutCategoryRowItemBinding
import com.arulvakku.lyrics.app.ui.listeners.CellClickListenerSongs
import com.arulvakku.lyrics.app.ui.view.home.song.SongModel


class SongsAdapter(
    var context: Context,
    private val categoryItems: List<SongModel>,
    private val cellClickListenerSongs: CellClickListenerSongs
) :
    RecyclerView.Adapter<SongsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int = categoryItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categoryItems[position]
        holder.bind(category, position)
        holder.itemView.setOnClickListener {
            cellClickListenerSongs.onSongCellClickListener(categoryItems.get(position),position)
        }
    }


    class ViewHolder private constructor(private val binding: LayoutCategoryRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(songResult: SongModel, position: Int) {
            binding.txtCategoryTitle.text = songResult.sTitle
            val rowCount = position + 1
            binding.txtCount.text = "$rowCount."
            binding.textCategoryName.text  = songResult.sCategory
            binding.textCategoryName.visibility = View.GONE
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutCategoryRowItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}