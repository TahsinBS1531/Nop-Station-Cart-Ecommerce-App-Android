package com.example.nopstationcart.view.Single_Category_Page

import com.example.nopstationcart.R
import com.example.nopstationcart.model.data.CategoryItem
import com.example.nopstationcart.model.data.categoryDetailsItem


class foodCategorySingleitemList {
    public fun getProducts(): ArrayList<categoryDetailsItem> {
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


        var categoryList = arrayListOf<categoryDetailsItem>()

        for (index in categoryImage.indices){
            val item = categoryDetailsItem(categoryText[index],categoryImage[index])
            categoryList.add(item)
        }

        return categoryList
    }
}