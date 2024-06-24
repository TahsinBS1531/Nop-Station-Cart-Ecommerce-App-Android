package com.example.nopstationcart.Services.Model.Home_Page.Slider

import okhttp3.Interceptor
import okhttp3.Response

class sliderAuthInterceptor() :Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .header("Accept", "application/json")
            .header("Content-Type","application/json")
            .header("DeviceId","44b4d8cd-7a2d-4a5f-a0e2-798021f1e294")
            .header("NST","eyJhbGciOiJIUzUxMiJ9.eyJOU1RfS0VZIjoiYm05d1UzUmhkR2x2YmxSdmEyVnUifQ.adqiIzFjqZdpJw5uHOHjE5qw2UvCDH2FwMmwlYvr5ljKyPG65ZQe_4wb8NYEQFXmyZZyVu-77xd5Njn310cjMw")
            .header("User-Agent","com.bs.ecommerce/1.0")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
