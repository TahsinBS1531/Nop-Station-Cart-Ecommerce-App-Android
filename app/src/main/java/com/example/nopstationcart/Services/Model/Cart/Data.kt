package com.example.nopstationcart.Services.Model.Cart

data class Data(
    val RedirectToDetailsPage: Boolean,
    val RedirectToShoppingCartPage: Boolean,
    val RedirectToWishListPage: Boolean,
    val TotalShoppingCartProducts: Int,
    val TotalWishListProducts: Int
)