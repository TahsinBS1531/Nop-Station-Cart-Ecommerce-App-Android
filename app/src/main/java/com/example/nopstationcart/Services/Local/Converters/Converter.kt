package com.example.nopstationcart.Services.Local.Converters

import androidx.room.TypeConverter
import com.example.nopstationcart.Services.Model.CategoryList.CustomProperties
import com.example.nopstationcart.Services.Model.CategoryList.Product
import com.example.nopstationcart.Services.Model.CategoryList.SubCategory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromCustomProperties(value: CustomProperties): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toCustomProperties(value: String): CustomProperties {
        val type = object : TypeToken<CustomProperties>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromProducts(value: List<Product>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toProducts(value: String): List<Product> {
        val type = object : TypeToken<List<Product>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromSubCategories(value: List<SubCategory>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toSubCategories(value: String): List<SubCategory> {
        val type = object : TypeToken<List<SubCategory>>() {}.type
        return Gson().fromJson(value, type)
    }
}