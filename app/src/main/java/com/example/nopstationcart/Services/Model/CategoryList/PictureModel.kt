package com.example.nopstationcart.Services.Model.CategoryList

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PictureModel(
    val AlternateText: String,
    val CustomProperties: CustomProperties,
    val FullSizeImageUrl: String,
    val ImageUrl: String,
    val ThumbImageUrl: String,
    val Title: String
):Parcelable