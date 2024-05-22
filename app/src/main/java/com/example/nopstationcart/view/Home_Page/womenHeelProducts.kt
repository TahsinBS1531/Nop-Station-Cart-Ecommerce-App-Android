package com.example.nopstationcart.view.Home_Page

import com.example.nopstationcart.R
import com.example.nopstationcart.model.data.womenHeelItems

class womenHeelProducts {

    public fun getProducts(): ArrayList<womenHeelItems> {
        val featuredImg = arrayOf(
            R.drawable.women_heel_img1,
            R.drawable.women_heel_img2,
            R.drawable.women_heel_img3,
            R.drawable.women_heel_img4,
            R.drawable.women_heel_img5
        )

        val featuredTittle = arrayOf(
            "Women Heel Products 1",
            "Women Heel Products 2",
            "Women Heel Products 3",
            "Women Heel Products 4",
            "Women Heel Products 5"
        )

        val featuredPrice = arrayOf(
            "$20.00",
            "$30.00",
            "$20.00",
            "$30.00",
            "$10.00"
        )

        val featuredRatting = arrayOf(
            3.5f,
            4.5f,
            5f,
            2.5f,
            1.5f
        )

        var featuredArrayList = arrayListOf<womenHeelItems>()

        for (index in featuredImg.indices){
            val item = womenHeelItems(featuredTittle[index],featuredImg[index],featuredPrice[index],featuredRatting[index])
            featuredArrayList.add(item)
        }

        return featuredArrayList
    }
}