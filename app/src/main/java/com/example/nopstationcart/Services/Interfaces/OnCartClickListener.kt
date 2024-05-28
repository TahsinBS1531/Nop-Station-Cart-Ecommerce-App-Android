package com.example.nopstationcart.Services.Interfaces

interface OnCartClickListener {
    fun onRemoveCart(position:Int, productId:String)
    fun onIncreaseCart(position:Int, productId:String, quantity:String)
    fun onDecreasecart(position:Int,productId:String, quantity:String)
}