package com.example.nopstationcart.Services.Local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nopstationcart.Services.Model.ShoppingCart.Item
import com.example.nopstationcart.Services.Model.ShoppingCart.productCartItems

@Entity(tableName = "order_details")
data class OrderDetailsEntity(
    @PrimaryKey var orderId: String,
    val existing_address: String,
    val billing_address: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val company: String,
    val country: String,
    val state:String,
    val zip:String,
    val city:String,
    val phone_number:String,
    val fax_number:String,
    val total_amount:String,
    val products:List<productCartItems>

)
