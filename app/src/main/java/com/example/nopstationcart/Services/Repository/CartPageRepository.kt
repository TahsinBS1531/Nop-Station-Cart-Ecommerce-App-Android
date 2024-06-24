package com.example.nopstationcart.Services.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nopstationcart.Services.Model.Cart.CartBodyRequest
import com.example.nopstationcart.Services.Model.Cart.CartResponse
import com.example.nopstationcart.Services.Netwrok.AddToCartApiInterface
import com.example.nopstationcart.utils.NetworkResult
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CartPageRepository @Inject constructor (@ApplicationContext private val context: Context, private val apiClient: AddToCartApiInterface) {

    private val _responseLiveData = MutableLiveData<NetworkResult<CartResponse>>()
    val responseLiveData: LiveData<NetworkResult<CartResponse>>
        get() = _responseLiveData

    suspend fun addProductToCart(request: CartBodyRequest, productID:Int){
        _responseLiveData.postValue(NetworkResult.Loading())
        val response = apiClient.getAddToCartApi(productID,request)
        handleResponse(response)
    }

    private fun handleResponse(response: Response<CartResponse>) {
        if(response.isSuccessful && response.body()!=null){
            println("Add to cart Response Successful")
            _responseLiveData.postValue(NetworkResult.Success(response.body()))
        }else if(response.errorBody()!=null){
            println("Add to cart Not Successfully")
            _responseLiveData.postValue(NetworkResult.Error("Something went wrong",response.body()))
        }else{
            _responseLiveData.postValue(NetworkResult.Error("Something went wrong 2",response.body()))
        }

    }


//    fun addProductCart(request: CartBodyRequest, productID:Int):LiveData<Result<CartResponse>>{
//
//        val call = apiClient.getAddToCartApi(productID,request)
//        val _result = MutableLiveData<Result<CartResponse>>()
//
//        call.enqueue(object: Callback<CartResponse>{
//            override fun onResponse(p0: Call<CartResponse>, response: Response<CartResponse>) {
//                if(response.isSuccessful){
//
//                    response.body()?.let {
//                        if(response.code() ==400){
//                            _result.postValue(Result.failure(Throwable("This can't be added")))
//                        }else{
//                            _result.postValue(Result.success(it))
//                        }
//                    }?:_result.postValue(Result.failure(Throwable("Response Body is Null")))
//                }
//                else{
//                    val errorMessage = when (response.code()) {
//                        400 -> "This product cannot be added to the cart"
//                        else -> response.errorBody()?.string() ?: "Unknown error occurred"
//                    }
//                    _result.postValue(Result.failure(Throwable(errorMessage)))
//                }
//            }
//
//            override fun onFailure(p0: Call<CartResponse>, p1: Throwable) {
//                _result.postValue(Result.failure(Throwable("request is denied")))
//            }
//
//        })
//        return _result
//    }
}