package com.example.nopstationcart.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopstationcart.Services.Local.AppDatabase
import com.example.nopstationcart.Services.Model.Home_Page.Slider.Slider
import com.example.nopstationcart.Services.Model.Home_Page.Slider.SliderResponse
import com.example.nopstationcart.Services.Model.login.loginResponse
import com.example.nopstationcart.Services.Repository.SliderRespository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SliderViewModel @Inject constructor (private val repository:SliderRespository):ViewModel() {
    private val _sliderResult = MutableLiveData<Result<List<Slider>>>()
    val sliderResult: LiveData<Result<List<Slider>>> = _sliderResult
    //private val sliderRepo = SliderRespository()


    fun getSlider(){
        viewModelScope.launch {
            val result = repository.getSlideData()
            result.observeForever { _sliderResult.postValue(it) }
        }

    }

}