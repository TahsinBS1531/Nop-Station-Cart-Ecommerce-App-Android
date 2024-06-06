package com.example.nopstationcart.Services.Model.CategoryList

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "category")
data class Data(
    val CustomProperties: CustomProperties,
    @PrimaryKey
    val Id: Int,
    val Name: String,
    val Products: List<Product>,
    val SeName: String,
    val SubCategories: List<SubCategory>
)