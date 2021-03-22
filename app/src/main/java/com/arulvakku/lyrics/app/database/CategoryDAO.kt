package com.arulvakku.lyrics.app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arulvakku.lyrics.app.data.Category


@Dao
public interface CategoryDAO {
    @Query("SELECT * FROM song_category")
    fun getAllCategoryList(): List<Category>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(category: Category)

}