package com.example.nopstationcart.view.logOut

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nopstationcart.model.interfaces.logOutApiInterface
import com.example.nopstationcart.view.Home_Page.Home_Page
import com.example.nopstationcart.view.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class logOutHandler(private val context:Context, private val sharedPreferences:SharedPreferences) {

    val retrofit = Retrofit.Builder()
        .baseUrl("http://demo460.nop-station.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val logOutApi = retrofit.create(logOutApiInterface::class.java)
    fun getLogOut(){

        val sharedPreferences = MainActivity().sharedPreferences
        val token = sharedPreferences.getString("Token",null)
        if(token!=null){
            val call = logOutApi.logOut(token,"application/json","44b4d8cd-7a2d-4a5f-a0e2-798021f1e294","eyJhbGciOiJIUzUxMiJ9.eyJOU1RfS0VZIjoiYm05d1UzUmhkR2x2YmxSdmEyVnUifQ.adqiIzFjqZdpJw5uHOHjE5qw2UvCDH2FwMmwlYvr5ljKyPG65ZQe_4wb8NYEQFXmyZZyVu-77xd5Njn310cjMw","com.bs.ecommerce/1.0")
            call.enqueue(object : Callback<Void?> {
                override fun onResponse(p0: Call<Void?>, p1: Response<Void?>) {
                    sharedPreferences.edit().remove("Token").apply()
                }

                override fun onFailure(p0: Call<Void?>, p1: Throwable) {
                    Toast.makeText(context,"Error occured in the Api Calling ${p1.message}",Toast.LENGTH_LONG).show()
                }
            })
        }else{
            Toast.makeText(context,"Token is already Empty",Toast.LENGTH_LONG).show()
        }
    }
}