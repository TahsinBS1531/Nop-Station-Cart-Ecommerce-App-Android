package com.example.nopstationcart.Services.Repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.LiveData
import com.example.nopstationcart.Services.Local.FeaturedProductDao
import com.example.nopstationcart.Services.Local.FeaturedProductsDao
import com.example.nopstationcart.Services.Local.FeaturedProductsEntity
import com.example.nopstationcart.Services.Model.Home_Page.Featured_Products.FeaturedProductsResponse
import com.example.nopstationcart.Services.Model.Home_Page.Featured_Products.toEntity
import com.example.nopstationcart.Services.Netwrok.featuredProductsApiInterface
import com.example.nopstationcart.Services.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FeaturedProductsRepository(private val featuredProductDao: FeaturedProductsDao, private val context: Context) {
    private val instance = RetrofitInstance()

    val featuredProducts: LiveData<List<FeaturedProductsEntity>> = featuredProductDao.getAllFeaturedProducts()

    suspend fun fetchAndCacheFeaturedProducts() {
        if (isNetworkAvailable(context)) {
            try {
                val productsResponse = fetchFeaturedProductsFromApi()
                productsResponse?.let { products ->
                    val entities = products.Data.map { it.toEntity() }
                    withContext(Dispatchers.IO) {
                        featuredProductDao.deleteAll()
                        featuredProductDao.insertAll(entities)
                    }
                }
            } catch (e: Exception) {
                // Handle the exception, e.g., log it
            }
        }
    }

    private suspend fun fetchFeaturedProductsFromApi(): FeaturedProductsResponse? = suspendCoroutine { continuation ->
        val call = instance.getRetrofitInstance().create(featuredProductsApiInterface::class.java).getApiData()
        call.enqueue(object : Callback<FeaturedProductsResponse> {
            override fun onResponse(call: Call<FeaturedProductsResponse>, response: Response<FeaturedProductsResponse>) {
                if (response.isSuccessful) {
                    continuation.resume(response.body())
                } else {
                    continuation.resume(null)
                }
            }

            override fun onFailure(call: Call<FeaturedProductsResponse>, t: Throwable) {
                continuation.resume(null)
            }
        })
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            @Suppress("DEPRECATION")
            val activeNetworkInfo = connectivityManager.activeNetwork ?: return false
            activeNetworkInfo.isConnected
        }
    }
}
