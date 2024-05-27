package com.example.nopstationcart.Services.Netwrok

import com.example.nopstationcart.Services.Model.CategoryList.CategoryListResponse
import com.example.nopstationcart.Services.Model.CategoryTree.CategoryTreeResponse
import com.example.nopstationcart.Services.Model.Home_Page.Featured_Products.FeaturedProductsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface categoryTreeApiInterface {

    @GET("home/categorytree")
    fun getApiData(@Header("Content-Type") content_type:String = "application/json",
                   @Header("DeviceId") deviceId:String ="44b4d8cd-7a2d-4a5f-a0e2-798021f1e294",
                   @Header("NST") nst:String = "eyJhbGciOiJIUzUxMiJ9.eyJOU1RfS0VZIjoiYm05d1UzUmhkR2x2YmxSdmEyVnUifQ.adqiIzFjqZdpJw5uHOHjE5qw2UvCDH2FwMmwlYvr5ljKyPG65ZQe_4wb8NYEQFXmyZZyVu-77xd5Njn310cjMw")
    :Call<CategoryTreeResponse>
}