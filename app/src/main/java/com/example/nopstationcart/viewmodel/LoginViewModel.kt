package com.example.nopstationcart.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopstationcart.Services.LoginApiClient
import com.example.nopstationcart.Services.Model.login.Data
import com.example.nopstationcart.Services.Model.login.UploadPicture
import com.example.nopstationcart.Services.Model.login.loginDataClass
import com.example.nopstationcart.Services.Model.login.loginResponse
import com.example.nopstationcart.Services.Repository.LoginRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel : ViewModel() {
    lateinit var FormValues: List<Any>
    lateinit var UploadPicture: UploadPicture

    private val repository = LoginRepository()
    private val _loginResult = MutableLiveData<Result<loginResponse>>()
    val loginResult: LiveData<Result<loginResponse>> = _loginResult

fun login(username:String, password:String){
    val data = Data(username = username, Email = username, Password = password)
    UploadPicture = UploadPicture()
    FormValues = listOf()
    val loginRequest = loginDataClass(data,FormValues,UploadPicture)
    val result = repository.getLoginData(loginRequest)
    result.observeForever{_loginResult.postValue(it)}

}

    fun saveToken(context: Context, token: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("TOKEN", token)
        editor.apply()
        println("Token : $token")
    }
}
