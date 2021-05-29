package com.arulvakku.lyrics.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
//@Database(entities = arrayOf(),version = 1,exportSchema = false)
abstract class ArulvakkuDatabase : RoomDatabase() {
    companion object {
        val DATABASE_NAME: String = "arulvakku.db"
    }
}