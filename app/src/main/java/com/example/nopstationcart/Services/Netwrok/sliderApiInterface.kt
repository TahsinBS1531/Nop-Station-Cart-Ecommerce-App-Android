package com.example.nopstationcart.Services.Netwrok

import com.example.nopstationcart.Services.Model.Home_Page.Slider.SliderResponse
import com.example.nopstationcart.Services.Model.login.loginDataClass
import com.example.nopstationcart.Services.Model.login.loginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

interface sliderApiInterface {

    @GET("slider/homepageslider")
    fun getApiData():Call<SliderResponse>

}