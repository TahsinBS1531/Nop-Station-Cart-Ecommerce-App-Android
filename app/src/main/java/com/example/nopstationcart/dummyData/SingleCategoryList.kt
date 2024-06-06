package com.example.nopstationcart.dummyData

import com.example.nopstationcart.R

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