package com.sembiyan.songs.app.listeners

interface TitleCellClickListener {
    fun onTitleCellClickListener(position: Int, categoryName: String, title: String, lyrics: String)
}