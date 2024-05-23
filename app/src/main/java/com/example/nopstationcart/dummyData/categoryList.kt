package com.example.nopstationcart.dummyData

import com.example.nopstationcart.R
import com.example.nopstationcart.Services.Model.CategoryItem


class categoryList {
    public fun getProducts(): ArrayList<CategoryItem> {
        val categoryImage = arrayOf(
            R.drawable.fruits1,
            R.drawable.furniture,
            R.drawable.phone,
            R.drawable.watch,
            R.drawable.furniture
        )

        val categoryText = arrayOf(
            "Food",
            "Furniture",
            "Phone",
            "Watch",
            "Furniture"
        )


        var categoryList = arrayListOf<CategoryItem>()

        for (index in categoryImage.indices){
            val item = CategoryItem(categoryImage[index],categoryText[index])
            categoryList.add(item)
        }

        return categoryList
    }
}