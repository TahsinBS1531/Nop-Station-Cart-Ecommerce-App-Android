package com.example.nopstationcart.Services.Model.CategoryList

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewOverviewModel(
    val AllowCustomerReviews: Boolean,
    val CanAddNewReview: Boolean,
    val CustomProperties: CustomProperties,
    val ProductId: Int,
    val RatingSum: Int,
    val TotalReviews: Int
):Parcelable