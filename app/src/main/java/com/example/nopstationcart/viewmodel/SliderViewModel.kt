package com.example.nopstationcart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nopstationcart.Services.Model.Home_Page.Slider.SliderResponse
import com.example.nopstationcart.Services.Model.login.loginResponse
import com.example.nopstationcart.Services.Repository.SliderRespository

class SliderViewModel:ViewModel() {
    private val _sliderResult = MutableLiveData<Result<SliderResponse>>()
    val sliderResult: LiveData<Result<SliderResponse>> = _sliderResult
    private val sliderRepo = SliderRespository()

    fun getSlider(){
        val result = sliderRepo.getSlideData()
        result.observeForever { _sliderResult.postValue(it) }
    }

}