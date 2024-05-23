package com.example.nopstationcart.Services.Repository

import com.example.nopstationcart.Services.Model.Home_Page.Slider.SliderResponse
import com.example.nopstationcart.Services.SliderApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SliderRespository {
    private val call = SliderApiClient.apiService.getApiData()

//    fun getSlider(): Response<SliderResponse>{
//        call.enqueue(object: Callback<SliderResponse>{
//            override fun onResponse(p0: Call<SliderResponse>, response: Response<SliderResponse>) {
//                if(response.body()!=null){
//                    //return response.body()
//                }
//            }
//
//            override fun onFailure(p0: Call<SliderResponse>, reponse: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        })
//    }
}