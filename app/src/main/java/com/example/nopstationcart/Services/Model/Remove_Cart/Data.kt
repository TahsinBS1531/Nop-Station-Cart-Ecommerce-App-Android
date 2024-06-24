package com.example.nopstationcart.Services.Model.Remove_Cart

data class Data(
    val AnonymousPermissed: Boolean,
    val Cart: Cart,
    val EstimateShipping: EstimateShipping,
    val OrderTotals: OrderTotals,
    val SelectedCheckoutAttributes: String
)