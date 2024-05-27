package com.example.nopstationcart.Services.Model.CategoryList

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductPrice(
    val AvailableForPreOrder: Boolean,
    val BasePricePAngV: String,
    val BasePricePAngVValue: Double,
    val CustomProperties: CustomProperties,
    val DisableAddToCompareListButton: Boolean,
    val DisableBuyButton: Boolean,
    val DisableWishlistButton: Boolean,
    val DisplayTaxShippingInfo: Boolean,
    val ForceRedirectionAfterAddingToCart: Boolean,
    val IsRental: Boolean,
    val OldPrice: String,
    val OldPriceValue: String,
    val PreOrderAvailabilityStartDateTimeUtc: String,
    val Price: String,
    val PriceValue: Double
):Parcelable