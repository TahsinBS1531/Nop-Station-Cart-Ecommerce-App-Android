package com.example.nopstationcart.Services.Model.Product_Search

data class Data(
    val AvailableCategories: List<AvailableCategory>,
    val AvailableManufacturers: List<AvailableManufacturer>,
    val AvailableVendors: List<Any>,
    val CatalogProductsModel: CatalogProductsModel,
    val CustomProperties: CustomPropertiesXXXXXXXXXXX,
    val advs: Boolean,
    val asv: Boolean,
    val cid: Int,
    val isc: Boolean,
    val mid: Int,
    val q: String,
    val sid: Boolean,
    val vid: Int
)