package com.example.nopstationcart.Services.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nopstationcart.Services.Local.BannerDao
import com.example.nopstationcart.Services.Model.Home_Page.Slider.Slider
import com.example.nopstationcart.Services.Model.Home_Page.Slider.SliderResponse
import com.example.nopstationcart.Services.Model.login.loginResponse
import com.example.nopstationcart.Services.Netwrok.InternetStatus
import com.example.nopstationcart.Services.Netwrok.sliderApiInterface
import com.example.nopstationcart.Services.SliderApiClient
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SliderRespository @Inject constructor(private val bannerDao:BannerDao, @ApplicationContext private val context: Context, private val sliderApiClient:sliderApiInterface) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun getSlideData():LiveData<Result<List<Slider>>>{
        val result = MutableLiveData<Result<List<Slider>>>()

        if(InternetStatus.isOnline(context)){
            val call = sliderApiClient.getApiData()
            call.enqueue(object : Callback<SliderResponse>{
                override fun onResponse(p0: Call<SliderResponse>, response: Response<SliderResponse>) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            val sliderList = it.Data.Sliders.map {
                                Slider(EntityId = it.EntityId, Id = it.Id, ImageUrl = it.ImageUrl, SliderType = it.SliderType)
                            }
                            coroutineScope.launch {
                                bannerDao.saveBannerList(sliderList)
                                withContext(Dispatchers.Main){
                                    result.postValue(Result.success(sliderList))
                                }
                            }

                        }?: result.postValue(Result.failure(Throwable("Response body is null")))
                    }else{
                        result.postValue(Result.failure(Throwable("Request is denied ")))
                    }
                }

                override fun onFailure(p0: Call<SliderResponse>, p1: Throwable) {
                    result.postValue(Result.failure(p1))
                }

            })
        }else{

            getSliderFromLocal(result)
//            coroutineScope.launch {
//                try {
//                    val localData = bannerDao.getBannerList()
//                    withContext(Dispatchers.Main) {
//                        result.postValue(Result.success(localData))
//                    }
//                } catch (e: Exception) {
//                    withContext(Dispatchers.Main) {
//                        result.postValue(Result.failure(e))
//                    }
//                }
//            }

        }

        return result
    }

    private fun getSliderFromLocal(result: MutableLiveData<Result<List<Slider>>>){
        coroutineScope.launch {
            try {
                val localData = bannerDao.getBannerList()
                withContext(Dispatchers.Main) {
                    result.postValue(Result.success(localData))
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    result.postValue(Result.failure(e))
                }
            }
        }
    }
}