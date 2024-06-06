package com.example.nopstationcart.dummyData

import com.example.nopstationcart.R

class bestSellingProducts {

    public fun getProducts(): ArrayList<bestSellingItem> {
        val bestSellingImage = arrayOf(
            R.drawable.bestselling1,
            R.drawable.bestsellingwatch,
            R.drawable.watch,
            R.drawable.fruits1,
            R.drawable.bestselling1
        )

        val bestSellingTittle = arrayOf(
            "California orange 8 pcs",
            "California Watch 1 pcs",
            "Naviforce Watch 1 pcs",
            "Lichi",
            "orange Fruits"
        )

        val bestSellingPrice = arrayOf(
            "$15.00",
            "$38.00",
            "$20.00",
            "$30.00",
            "$10.00"
        )

        var bestSellingList = arrayListOf<bestSellingItem>()

        for (index in bestSellingImage.indices){
            val item = bestSellingItem(bestSellingTittle[index],bestSellingImage[index],bestSellingPrice[index])
            bestSellingList.add(item)
        }

        return bestSellingList
    }


}