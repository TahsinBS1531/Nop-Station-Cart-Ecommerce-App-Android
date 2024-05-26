package com.example.nopstationcart.Services.Model.CategoryList

data class SubCategory(
    val CustomProperties: CustomProperties,
    val Id: Int,
    val Name: String,
    val Products: List<Any>,
    val SeName: String,
    val SubCategories: List<Any>
)