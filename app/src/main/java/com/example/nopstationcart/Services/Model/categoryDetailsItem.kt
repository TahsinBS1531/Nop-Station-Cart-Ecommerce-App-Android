package com.example.nopstationcart.Services.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class categoryDetailsItem(var tittle:String,var imageResID:Int, var itemPrice:String):Parcelable

