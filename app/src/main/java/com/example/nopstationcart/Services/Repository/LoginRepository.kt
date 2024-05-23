package com.example.nopstationcart.Services.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nopstationcart.Services.LoginApiClient
import com.example.nopstationcart.Services.Model.login.loginAuthInterceptor
import com.example.nopstationcart.Services.Model.login.loginDataClass
import com.example.nopstationcart.Services.Model.login.loginResponse
import com.example.nopstationcart.Services.Netwrok.loginApiInterface
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginRepository {

    fun getLoginData(loginData: loginDataClass): LiveData<Result<loginResponse>> {
        val result = MutableLiveData<Result<loginResponse>>()
        val call = LoginApiClient.apiService.getApiData(loginData)
        call.enqueue(object : Callback<loginResponse> {
            override fun onResponse(call: Call<loginResponse>, response: Response<loginResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        result.postValue(Result.success(it))
                    } ?: result.postValue(Result.failure(Throwable("Response body is null")))
                } else {
                    result.postValue(Result.failure(Throwable("Error code: ${response.code()}")))
                }
            }

            override fun onFailure(call: Call<loginResponse>, t: Throwable) {
                result.postValue(Result.failure(t))
            }
        })
        return result
    }
}
