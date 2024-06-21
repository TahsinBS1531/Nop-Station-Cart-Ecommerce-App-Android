package com.example.nopstationcart.Services.Model.ShoppingCart

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@kotlinx.parcelize.Parcelize
data class productCartItems(val tittle:String, val price:String, val imageResID:String, var quantity:Int, val productId:Int):Parcelable
