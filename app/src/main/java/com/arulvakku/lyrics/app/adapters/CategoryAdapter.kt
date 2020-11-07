package com.arulvakku.lyrics.app.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arulvakku.lyrics.app.BR
import com.arulvakku.lyrics.app.R
import com.arulvakku.lyrics.app.data.Category
import com.arulvakku.lyrics.app.databinding.LayoutCategoryRowItemBinding
import com.sembiyan.songs.app.listeners.CellClickListener
import kotlinx.android.synthetic.main.layout_category_row_item.view.*

class CategoryAdapter(
    var context: Context,
    private val items: List<Category>,
    private val cellClickListener: CellClickListener
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.ViewHolder {
        val binding: LayoutCategoryRowItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.layout_category_row_item, parent, false
        )
        return CategoryAdapter.ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        holder.bind(items.get(position))

        holder.itemView.materialCardView.setCardBackgroundColor(Color.parseColor(items.get(position).color_code))
        holder.itemView.materialCardView.radius = 10F
        holder.itemView.materialCardView.elevation = 5F

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(items.get(position).title)
        }
    }

    // Creating ViewHolder
    class ViewHolder(val binding: LayoutCategoryRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Any) {
            binding.setVariable(
                BR.category,
                data
            ) //BR - generated class; BR.user - 'user' is variable name declared in layout
            binding.executePendingBindings()
        }
    }


}