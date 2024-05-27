package com.example.nopstationcart.Services.Model.CategoryList

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductSpecificationModel(
    val CustomProperties: CustomProperties,
    val Groups: List<String>
):Parcelable