package com.example.nopstationcart.Services.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nopstationcart.Services.Model.Home_Page.Slider.SliderResponse
import com.example.nopstationcart.Services.Model.login.loginResponse
import com.example.nopstationcart.Services.SliderApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SliderRespository {

    fun getSlideData():LiveData<Result<SliderResponse>>{
        val result = MutableLiveData<Result<SliderResponse>>()

        val call = SliderApiClient.apiService.getApiData()
        call.enqueue(object : Callback<SliderResponse>{
            override fun onResponse(p0: Call<SliderResponse>, response: Response<SliderResponse>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        result.postValue(Result.success(it))
                    }?: result.postValue(Result.failure(Throwable("Response body is null")))
                }else{
                    result.postValue(Result.failure(Throwable("Request is denied ")))
                }
            }

            override fun onFailure(p0: Call<SliderResponse>, p1: Throwable) {
                result.postValue(Result.failure(p1))
            }

        })
        return result
    }
}