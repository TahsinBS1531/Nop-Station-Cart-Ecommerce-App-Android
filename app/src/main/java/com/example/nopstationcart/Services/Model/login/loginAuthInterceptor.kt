package com.example.nopstationcart.Services.Model.login

import okhttp3.Interceptor
import okhttp3.Response

class loginAuthInterceptor(private val deviceId:String,private val nst:String, private val userAgent:String):Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .header("Content-Type", "application/json")
            .header("DeviceId", deviceId)
            .header("NST", nst)
            .header("User-Agent", userAgent)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}