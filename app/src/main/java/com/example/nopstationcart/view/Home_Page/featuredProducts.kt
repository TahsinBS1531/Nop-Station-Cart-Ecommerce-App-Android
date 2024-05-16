package com.example.nopstationcart.view.Home_Page

import com.example.nopstationcart.R
import com.example.nopstationcart.model.data.bestSellingItem
import com.example.nopstationcart.model.data.featuredProductsItem

class featuredProducts {

    public fun getProducts(): ArrayList<featuredProductsItem> {
        val featuredImg = arrayOf(
            R.drawable.featured_product_img1,
            R.drawable.featured_products_item2,
            R.drawable.featured_products_img3,
            R.drawable.featured_product_img4,
            R.drawable.featured_products_img5
        )

        val featuredTittle = arrayOf(
            "Featured Products 1",
            "Featured Products 2",
            "Featured Products 3",
            "Featured Products 4",
            "Featured Products 5"
        )

        val featuredPrice = arrayOf(
            "$25.00",
            "$38.00",
            "$20.00",
            "$30.00",
            "$10.00"
        )

        val featuredRatting = arrayOf(
            3f,
            4f,
            5f,
            2f,
            1f
        )

        var featuredArrayList = arrayListOf<featuredProductsItem>()

        for (index in featuredImg.indices){
            val item = featuredProductsItem(featuredTittle[index],featuredImg[index],featuredPrice[index],featuredRatting[index])
            featuredArrayList.add(item)
        }

        return featuredArrayList
    }
}