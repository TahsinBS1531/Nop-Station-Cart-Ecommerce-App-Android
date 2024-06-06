package com.example.nopstationcart.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopstationcart.Services.Local.AppDatabase
import com.example.nopstationcart.Services.Local.BannerDao
import com.example.nopstationcart.Services.Model.Home_Page.Slider.Slider
import com.example.nopstationcart.Services.Model.Home_Page.Slider.SliderResponse
import com.example.nopstationcart.Services.Model.login.loginResponse
import com.example.nopstationcart.Services.Netwrok.sliderApiInterface
import com.example.nopstationcart.Services.Repository.SliderRespository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SliderViewModel:ViewModel() {
    private val _sliderResult = MutableLiveData<Result<List<Slider>>>()
    val sliderResult: LiveData<Result<List<Slider>>> = _sliderResult
    //private val sliderRepo = SliderRespository()


    fun getSlider(context:Context, bannerDao: BannerDao){
        val repository=SliderRespository(bannerDao)
        viewModelScope.launch {
            val result = repository.getSlideData(context)
            result.observeForever { _sliderResult.postValue(it) }
        }

    }

}