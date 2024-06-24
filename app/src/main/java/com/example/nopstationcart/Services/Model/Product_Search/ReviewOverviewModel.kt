package com.example.nopstationcart.Services.Model.Product_Search

data class ReviewOverviewModel(
    val AllowCustomerReviews: Boolean,
    val CanAddNewReview: Boolean,
    val CustomProperties: CustomPropertiesXXXXXXXXXXX,
    val ProductId: Int,
    val RatingSum: Int,
    val TotalReviews: Int
)