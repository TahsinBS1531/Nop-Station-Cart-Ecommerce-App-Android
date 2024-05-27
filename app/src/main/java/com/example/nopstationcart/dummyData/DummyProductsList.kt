package com.example.nopstationcart.dummyData

import com.example.nopstationcart.R
import com.example.nopstationcart.Services.Model.categoryDetailsItem


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
                    "Food Item 1",
                    "Food Item 2",
                    "Food Item 3",
                    "Food Item 4",
                    "Food Item 5"
                )

                val categoryPrice = arrayOf(
                    "20.00$",
                    "60.00$",
                    "30.00$",
                    "40.00$",
                    "50.00$"
                )


                for (index in categoryImage.indices){
                    val item = categoryDetailsItem(categoryText[index],categoryImage[index],categoryPrice[index])
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
                    "Furniture Item 1",
                    "Furniture Item 2",
                    "Furniture Item 3",
                    "Furniture Item 4",
                    "Furniture Item 5"
                )

                val categoryPrice = arrayOf(
                    "20.00$",
                    "60.00$",
                    "30.00$",
                    "40.00$",
                    "50.00$"
                )


                for (index in categoryImage.indices){
                    val item = categoryDetailsItem(categoryText[index],categoryImage[index],categoryPrice[index])
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
                    "Phone Item 1",
                    "Phone Item 2",
                    "Phone Item 3",
                    "Phone Item 4",
                    "Phone Item 5"
                )

                val categoryPrice = arrayOf(
                    "20.00$",
                    "60.00$",
                    "30.00$",
                    "40.00$",
                    "50.00$"
                )


                for (index in categoryImage.indices){
                    val item = categoryDetailsItem(categoryText[index],categoryImage[index],categoryPrice[index])
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
                    "Watch Item 1",
                    "Watch Item 2",
                    "Watch Item 3",
                    "Watch Item 4",
                    "Watch Item 5"
                )

                val categoryPrice = arrayOf(
                    "20.00$",
                    "60.00$",
                    "30.00$",
                    "40.00$",
                    "50.00$"
                )


                for (index in categoryImage.indices){
                    val item = categoryDetailsItem(categoryText[index],categoryImage[index],categoryPrice[index])
                    categoryList.add(item)
                }

                return categoryList
            }

        }

        return categoryList

    }
}