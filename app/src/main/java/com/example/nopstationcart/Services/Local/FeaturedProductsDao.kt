package com.example.nopstationcart.Services.Local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FeaturedProductsDao {
    @Query("SELECT * FROM featured_products")
    fun getAllFeaturedProducts(): LiveData<List<FeaturedProductsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<FeaturedProductsEntity>)

    @Query("DELETE FROM featured_products")
    suspend fun deleteAll()
}