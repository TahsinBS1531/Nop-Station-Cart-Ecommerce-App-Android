package com.example.nopstationcart.view.Category_PAge

import com.example.nopstationcart.R
import com.example.nopstationcart.model.data.CategoryItem
import com.example.nopstationcart.model.data.singleCategoryItem

class singleCategoryList {

    public fun getProducts(): ArrayList<singleCategoryItem> {
        val categoryImage = arrayOf(
            R.drawable.fruits1,
            R.drawable.furniture,
            R.drawable.phone,
            R.drawable.watch,
            R.drawable.furniture,
            R.drawable.phone
        )

        val categoryText = arrayOf(
            "Fruits",
            "Furniture",
            "Phone",
            "Watch",
            "Furniture",
            "Phone"
        )


        var categoryList = arrayListOf<singleCategoryItem>()

        for (index in categoryImage.indices){
            val item = singleCategoryItem(categoryImage[index],categoryText[index])
            categoryList.add(item)
        }

        return categoryList
    }
}