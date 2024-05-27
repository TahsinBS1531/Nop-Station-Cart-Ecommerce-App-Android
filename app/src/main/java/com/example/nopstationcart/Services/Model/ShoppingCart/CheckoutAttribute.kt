package com.example.nopstationcart.Services.Model.ShoppingCart

data class CheckoutAttribute(
    val AllowedFileExtensions: List<Any>,
    val AttributeControlType: Int,
    val CustomProperties: CustomPropertiesXX,
    val DefaultValue: Any,
    val Id: Int,
    val IsRequired: Boolean,
    val Name: String,
    val SelectedDay: Any,
    val SelectedMonth: Any,
    val SelectedYear: Any,
    val TextPrompt: Any,
    val Values: List<Value>
)