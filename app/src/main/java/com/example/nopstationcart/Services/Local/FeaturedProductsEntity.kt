package com.example.nopstationcart.Services.Local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "featured_products")
data class FeaturedProductsEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: String,
    val imageUrl: String,
    val rating: Float,
    val shortDescription: String,
    val longDescription: String,
    val oldPrice: String
)
