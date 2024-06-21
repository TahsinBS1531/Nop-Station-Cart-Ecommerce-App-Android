package com.example.nopstationcart.Services.Netwrok

import com.example.nopstationcart.Services.Model.Product_Search.ProductSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductSearchApiInterface {
    @GET("catalog/search")
    suspend fun SearchProductsApi(@Query("q") query:String):Response<ProductSearchResponse>
}