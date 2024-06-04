package com.example.nopstationcart.Services.Local.Converters

import androidx.room.TypeConverter
import com.example.nopstationcart.Services.Model.CategoryList.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromProductList(value:List<Product>):String{
        val type = object : TypeToken<List<Product>>() {}.type
        return Gson().toJson(value, type)
    }
}