package com.example.nopstationcart.Services.Model.CategoryTree

data class Data(
    val CategoryId: Int,
    val IconUrl: String,
    val Name: String,
    val SeName: String,
    val SubCategories: List<SubCategory>
)