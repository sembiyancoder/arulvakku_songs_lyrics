package com.arulvakku.lyrics.app.utilities

import com.arulvakku.lyrics.app.ui.view.home.song.SongModel

class SongsSingleton {
    companion object{
        var songsList :ArrayList<SongModel> = ArrayList()

        fun getSongs():ArrayList<SongModel>{
            return songsList
        }

        fun setSongs(list : ArrayList<SongModel>){
            songsList = list
        }
    }

}