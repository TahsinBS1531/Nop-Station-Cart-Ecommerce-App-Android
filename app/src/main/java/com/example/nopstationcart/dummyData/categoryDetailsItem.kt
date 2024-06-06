package com.example.nopstationcart.dummyData

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class categoryDetailsItem(var tittle:String,var imageResID:Int, var itemPrice:String):Parcelable

