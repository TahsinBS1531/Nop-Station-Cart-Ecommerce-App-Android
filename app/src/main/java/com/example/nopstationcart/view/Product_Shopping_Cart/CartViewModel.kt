package com.example.nopstationcart.view.Product_Shopping_Cart

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopstationcart.Services.Model.Cart.CartBodyRequest
import com.example.nopstationcart.Services.Model.Cart.CartResponse
import com.example.nopstationcart.Services.Model.Cart.FormValue
import com.example.nopstationcart.Services.Model.Product_Search.ProductSearchResponse
import com.example.nopstationcart.Services.Repository.CartPageRepository
import com.example.nopstationcart.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor (private val repository: CartPageRepository):ViewModel(){
    lateinit var form: List<FormValue>

    val responseLiveData: LiveData<NetworkResult<CartResponse>>
        get() = repository.responseLiveData


    fun addToCart(id:Int,quantity:String){
        form = listOf(FormValue("addtocart_${id}.EnteredQuantity",quantity),
            FormValue("addtocart_${id}.EnteredGender","Male"))

        val requestBody = CartBodyRequest(form)
        viewModelScope.launch {
            repository.addProductToCart(requestBody,id)
        }
    }

}