package com.example.nopstationcart.Services.Local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OrderDetailsDao {

    @Query("SELECT * FROM order_details WHERE orderId = :orderId")
    suspend fun getOrder(orderId: String): OrderDetailsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: OrderDetailsEntity)

}