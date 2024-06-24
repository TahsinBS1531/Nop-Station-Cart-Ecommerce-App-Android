package com.example.nopstationcart.Services.Local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nopstationcart.Services.Model.CategoryList.Data


@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: List<Data>)

    @Query("SELECT * FROM category")
    suspend fun getCategory(): List<Data>

    @Query("DELETE FROM category")
    suspend fun deleteAll()

}