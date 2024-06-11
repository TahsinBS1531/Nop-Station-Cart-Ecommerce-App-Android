package com.example.nopstationcart.Services.Model

import com.example.nopstationcart.Services.Model.ShoppingCart.productCartItems

data class OrderDetailsItem(
    val name:String,
    val orderId:String,
    val email:String,
    val totalProducts:String,
    val orderStatus:String,
    val orderDate: String,
    val orderTotal:String,
    val phone:String,
    val fax:String,
    val billingAddress:String,
    val country:String,
    val city:String,
    val existingAddress:String,
    val state:String,
    val products:List<productCartItems>)
