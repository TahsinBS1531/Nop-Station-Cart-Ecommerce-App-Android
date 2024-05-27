package com.example.nopstationcart.Services.Model.Cart

data class CartResponse(
    val Data: Data,
    val ErrorList: List<Any>,
    val Message: String
)