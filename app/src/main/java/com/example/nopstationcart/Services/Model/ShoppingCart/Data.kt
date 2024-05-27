package com.example.nopstationcart.Services.Model.ShoppingCart

data class Data(
    val AnonymousPermissed: Boolean,
    val Cart: Cart,
    val EstimateShipping: EstimateShipping,
    val OrderTotals: OrderTotals,
    val SelectedCheckoutAttributes: String
)