package com.example.nopstationcart.Services.Model.ShoppingCart

import okhttp3.Interceptor
import okhttp3.Response

class ShoppingCartAuthInterceptor():Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("DeviceId","44b4d8cd-7a2d-4a5f-a0e2-798021f1e294")
            .addHeader("NST","eyJhbGciOiJIUzUxMiJ9.eyJOU1RfS0VZIjoiYm05d1UzUmhkR2x2YmxSdmEyVnUifQ.adqiIzFjqZdpJw5uHOHjE5qw2UvCDH2FwMmwlYvr5ljKyPG65ZQe_4wb8NYEQFXmyZZyVu-77xd5Njn310cjMw")
            .addHeader("User-Agent","com.bs.ecommerce/1.0")
            .build()
        return chain.proceed(request)
    }
}