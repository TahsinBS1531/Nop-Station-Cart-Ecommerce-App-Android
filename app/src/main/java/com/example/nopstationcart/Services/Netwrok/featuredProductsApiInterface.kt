package com.example.nopstationcart.Services.Netwrok

import com.example.nopstationcart.Services.Model.Home_Page.Featured_Products.FeaturedProductsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface featuredProductsApiInterface {

    @GET("home/featureproducts")
    fun getApiData(@Header("DeviceId") deviceId:String ="44e5f288-6902-4c46-8529-1f18dd27b8a3",
                   @Header("NST") nst:String = "eyJhbGciOiJIUzUxMiJ9.eyJOU1RfS0VZIjoiYm05d1UzUmhkR2x2YmxSdmEyVnUiLCJpYXQiOjE1OTE2NjU4MjB9.0mwdwiAi8z89AwZNDk6fKfOj8pGW9VtZq-1AzhnLmI--IqnntegRqg6OFMFxRsdxfQhXYSTWeoxsebuwHJQNbA")
    :Call<FeaturedProductsResponse>
}