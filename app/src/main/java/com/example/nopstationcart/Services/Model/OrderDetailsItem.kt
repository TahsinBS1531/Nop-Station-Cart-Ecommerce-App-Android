package com.example.nopstationcart.Services.Model

import com.example.nopstationcart.Services.Model.ShoppingCart.productCartItems

data class OrderDetailsItem(val name:String,val orderId:String,val email:String, val totalProducts:String,val products:List<productCartItems>)
