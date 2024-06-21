package com.example.nopstationcart.di

import com.example.nopstationcart.Services.Model.Home_Page.Slider.sliderAuthInterceptor
import com.example.nopstationcart.Services.Netwrok.ProductSearchApiInterface
import com.example.nopstationcart.Services.Netwrok.ProductSearchAuthIntercept
import com.example.nopstationcart.Services.Netwrok.sliderApiInterface
import com.example.nopstationcart.Services.SliderApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val BASE_URL = "http://demo460.nop-station.com/api/"

    @Provides
    @Singleton
    fun providesRetrofit(client:OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ProductSearchAuthIntercept())
            .build()
    }


    @Provides
    @Singleton
    fun providesProductSearchApiClient(retrofit: Retrofit, okHttpClient: OkHttpClient): ProductSearchApiInterface {
        return retrofit.newBuilder()
            .client(okHttpClient)
            .build()
            .create(ProductSearchApiInterface::class.java)
    }
}