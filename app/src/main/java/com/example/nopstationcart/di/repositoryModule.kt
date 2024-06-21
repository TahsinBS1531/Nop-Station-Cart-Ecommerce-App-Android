package com.example.nopstationcart.di

import com.example.nopstationcart.Services.Netwrok.ProductSearchApiInterface
import com.example.nopstationcart.Services.Repository.ProductSearchRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object repositoryModule {
    fun provideProductSearchRepository(
        productAPi: ProductSearchApiInterface
    ): ProductSearchRepository {
        return ProductSearchRepository(productAPi)
    }

}