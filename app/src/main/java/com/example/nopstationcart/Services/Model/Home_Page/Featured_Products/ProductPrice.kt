package com.example.nopstationcart.Services.Model.Home_Page.Featured_Products

data class ProductPrice(
    val AvailableForPreOrder: Boolean,
    val BasePricePAngV: Any,
    val BasePricePAngVValue: Double,
    val CustomProperties: CustomProperties,
    val DisableAddToCompareListButton: Boolean,
    val DisableBuyButton: Boolean,
    val DisableWishlistButton: Boolean,
    val DisplayTaxShippingInfo: Boolean,
    val ForceRedirectionAfterAddingToCart: Boolean,
    val IsRental: Boolean,
    val OldPrice: Any,
    val OldPriceValue: Any,
    val PreOrderAvailabilityStartDateTimeUtc: Any,
    val Price: String,
    val PriceValue: Double
)