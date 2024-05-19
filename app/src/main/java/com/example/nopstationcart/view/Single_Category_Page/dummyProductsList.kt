package com.example.nopstationcart.view.Single_Category_Page

import com.example.nopstationcart.R
import com.example.nopstationcart.model.data.categoryDetailsItem


class dummyProductsList {
    public fun getProducts(tittle:String): ArrayList<categoryDetailsItem> {

        var categoryList = arrayListOf<categoryDetailsItem>()
        when(tittle){
            "Food" -> {
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


                for (index in categoryImage.indices){
                    val item = categoryDetailsItem(categoryText[index],categoryImage[index])
                    categoryList.add(item)
                }

                return categoryList
            }
            "Furniture" -> {
                val categoryImage = arrayOf(
                    R.drawable.furniture1,
                    R.drawable.furniture2,
                    R.drawable.furniture3,
                    R.drawable.furnitur4,
                    R.drawable.furniture5
                )

                val categoryText = arrayOf(
                    "Item 1",
                    "furniture",
                    "Phone",
                    "Watch",
                    "Furniture"
                )


                for (index in categoryImage.indices){
                    val item = categoryDetailsItem(categoryText[index],categoryImage[index])
                    categoryList.add(item)
                }

                return categoryList
            }
            "Phone" -> {
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


                for (index in categoryImage.indices){
                    val item = categoryDetailsItem(categoryText[index],categoryImage[index])
                    categoryList.add(item)
                }

                return categoryList
            }
            "Watch" -> {
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


                for (index in categoryImage.indices){
                    val item = categoryDetailsItem(categoryText[index],categoryImage[index])
                    categoryList.add(item)
                }

                return categoryList
            }

        }

        return categoryList

    }
}