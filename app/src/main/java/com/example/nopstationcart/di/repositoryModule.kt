package com.example.nopstationcart.di

import android.app.Application
import android.content.Context
import com.example.nopstationcart.Services.Netwrok.AddToCartApiInterface
import com.example.nopstationcart.Services.Netwrok.ProductSearchApiInterface
import com.example.nopstationcart.Services.Repository.CartPageRepository
import com.example.nopstationcart.Services.Repository.ProductSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object repositoryModule {


    @Provides
    @Singleton
    fun provideProductSearchRepository(
        productAPi: ProductSearchApiInterface
    ): ProductSearchRepository {
        return ProductSearchRepository(productAPi)
    }

    @Provides
    @Singleton
    fun provideCartPageRepository(context: Context, apiClient: AddToCartApiInterface):CartPageRepository{
        return CartPageRepository(context,apiClient)
    }

}