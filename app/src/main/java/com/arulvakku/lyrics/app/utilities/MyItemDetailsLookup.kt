package com.arulvakku.lyrics.app.utilities

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.arulvakku.lyrics.app.activities.share.adapter.ShareLyricsAdapter

class MyItemDetailsLookup(private val recyclerView: RecyclerView) :
    ItemDetailsLookup<Long>() {
    override fun getItemDetails(event: MotionEvent): ItemDetails<Long>? {
        val view = recyclerView.findChildViewUnder(event.x, event.y)
        if (view != null) {
            return (recyclerView.getChildViewHolder(view) as ShareLyricsAdapter.ViewHolder)
                .getItemDetails()
        }
        return null
    }
}