package com.example.nopstationcart.view.Home_Page

import com.example.nopstationcart.R
import com.example.nopstationcart.model.data.CategoryItem


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
            "Item 1",
            "furniture",
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