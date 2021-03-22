package com.arulvakku.lyrics.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arulvakku.lyrics.app.data.Category

@Database(entities = arrayOf(Category::class), version = 1, exportSchema = false)

abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDAO(): CategoryDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "arulvakku_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }


    }

}

