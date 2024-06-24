package com.example.nopstationcart.Services.Model.Home_Page.Featured_Products

import com.example.nopstationcart.Services.Local.FeaturedProductsEntity

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

fun Data.toEntity(): FeaturedProductsEntity {
    return FeaturedProductsEntity(
        id = this.Id,
        name = this.Name,
        price = this.ProductPrice.Price,
        imageUrl = this.PictureModels.firstOrNull()?.ImageUrl ?: "",
        rating = if (this.ReviewOverviewModel.TotalReviews != 0)
            (this.ReviewOverviewModel.RatingSum / this.ReviewOverviewModel.TotalReviews).toFloat()
        else 0f,
        shortDescription = this.ShortDescription,
        longDescription = this.FullDescription,
        oldPrice = this.ProductPrice.OldPrice ?: ""
    )
}