package com.example.nopstationcart.Services.Model.Home_Page.Featured_Products

data class Data(
    val CustomProperties: CustomProperties,
    val FullDescription: String,
    val Id: Int,
    val MarkAsNew: Boolean,
    val Name: String,
    val PictureModels: List<PictureModel>,
    val ProductPrice: ProductPrice,
    val ProductSpecificationModel: ProductSpecificationModel,
    val ProductType: Int,
    val ReviewOverviewModel: ReviewOverviewModel,
    val SeName: String,
    val ShortDescription: String,
    val Sku: String
)