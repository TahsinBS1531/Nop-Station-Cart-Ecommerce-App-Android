package com.example.nopstationcart.Services.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nopstationcart.Services.Model.Product_Search.ProductSearchResponse
import com.example.nopstationcart.Services.Netwrok.ProductSearchApiInterface
import com.example.nopstationcart.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class ProductSearchRepository @Inject constructor(private val productAPi:ProductSearchApiInterface) {

    private val _responseLiveData = MutableLiveData<NetworkResult<ProductSearchResponse>>()
    val responseLiveData: LiveData<NetworkResult<ProductSearchResponse>>
        get() = _responseLiveData

    suspend fun productSearch(query:String){
        _responseLiveData.postValue(NetworkResult.Loading())
        val response = productAPi.SearchProductsApi(query)
        handleResponse(response)
    }

    private fun handleResponse(response: Response<ProductSearchResponse>) {
        if(response.isSuccessful && response.body()!=null){
            println("Query Response Successful")
            _responseLiveData.postValue(NetworkResult.Success(response.body()))
        }else if(response.errorBody()!=null){
            println("Query Not Successfull")
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _responseLiveData.postValue(NetworkResult.Error("Something went wrong",response.body()))
        }else{
            _responseLiveData.postValue(NetworkResult.Error("Something went wrong 2",response.body()))
        }
    }
}