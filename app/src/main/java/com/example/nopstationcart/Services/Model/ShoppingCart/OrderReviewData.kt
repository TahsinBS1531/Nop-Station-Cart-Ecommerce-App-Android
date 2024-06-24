package com.example.nopstationcart.Services.Model.ShoppingCart

data class OrderReviewData(
    val BillingAddress: BillingAddress,
    val CustomProperties: CustomPropertiesXX,
    val CustomValues: CustomValues,
    val Display: Boolean,
    val IsShippable: Boolean,
    val PaymentMethod: Any,
    val PickupAddress: PickupAddress,
    val SelectedPickupInStore: Boolean,
    val ShippingAddress: ShippingAddress,
    val ShippingMethod: Any
)