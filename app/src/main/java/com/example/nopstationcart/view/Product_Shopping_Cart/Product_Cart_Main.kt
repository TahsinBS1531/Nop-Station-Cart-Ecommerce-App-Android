package com.example.nopstationcart.view.Product_Shopping_Cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nopstationcart.R
import com.example.nopstationcart.Services.Interfaces.OnCartClickListener
import com.example.nopstationcart.Services.Model.Remove_Cart.RemoveCartRequest
import com.example.nopstationcart.Services.Model.ShoppingCart.productCartItems
import com.example.nopstationcart.Services.Model.Update_Cart.FormValue
import com.example.nopstationcart.Services.Model.Update_Cart.UpdateCartRequest
import com.example.nopstationcart.databinding.FragmentProductCartMainBinding
import com.example.nopstationcart.view.Adapters.productCartAdapter
import com.example.nopstationcart.viewmodel.RemoveCartViewModel
import com.example.nopstationcart.viewmodel.ShoppingCartViewModel
import com.example.nopstationcart.viewmodel.UpdateCartViewModel

class Product_Cart_Main : Fragment() {
    private val shoppingCartViewModel: ShoppingCartViewModel by viewModels()
    private val removeCartViewModel: RemoveCartViewModel by viewModels()
    private val updateCartViewModel:UpdateCartViewModel by viewModels()
    private lateinit var binding: FragmentProductCartMainBinding
    private val productsList = ArrayList<productCartItems>()
    private lateinit var adapter: productCartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product__cart__main, container, false)
        binding = FragmentProductCartMainBinding.bind(view)

        // Setup RecyclerView and Adapter
        binding.productCartRecycle.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = productCartAdapter(productsList,removeCartViewModel)
        binding.productCartRecycle.adapter = adapter

        // Observe LiveData
        var subTotal = 0.00
        shoppingCartViewModel.response.observe(viewLifecycleOwner) { result ->
            result.onSuccess { response ->
                productsList.clear() // Clear the list to avoid duplicates
                response.Data.Cart.Items.forEach {
                    val title = it.ProductName
                    val image = it.Picture.ImageUrl
                    val price = it.UnitPrice
                    val quantity = it.Quantity
                    val productId = it.Id
                    val item = productCartItems(tittle = title, imageResID = image, price = price, quantity = quantity,
                        productId = productId
                    )
                    productsList.add(item)

                    subTotal=it.SubTotalValue+subTotal
                }
                adapter.notifyDataSetChanged() // Notify adapter of data changes
                handlePrices(subTotal)
            }.onFailure {
                Toast.makeText(requireContext(), "Shopping cart Api call failed", Toast.LENGTH_LONG).show()
            }
        }

        shoppingCartViewModel.getCartProducts()
        handleBackBtn(view)
        handleCartBtn(adapter,productsList)



        return binding.root
    }

    fun handleBackBtn(view:View){
        binding.backbtnAppBar.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun handlePrices(subtotal:Double){
        binding.CartPageSubTotal.text = subtotal.toString()
        binding.CartPageTotall.text = subtotal.toString()
    }
    fun handleCartBtn(adapter:productCartAdapter, productList:ArrayList<productCartItems>){
        adapter.setOnItemClick(object:OnCartClickListener{

            override fun onRemoveCart(position: Int, productId: String) {
                removeFromCart(position,productId,productList)
            }

            override fun onIncreaseCart(position: Int, productId: String, quantity:String) {
                updateCartQuantity(position,productId,quantity)

            }

            override fun onDecreasecart(position: Int, productId: String,quantity:String) {
                updateCartQuantity(position,productId,quantity)
            }


        })
    }

    fun updateCartQuantity(position: Int, productId: String, quantity:String){
        val formValue = FormValue("itemquantity${productId}",quantity)
        val request = UpdateCartRequest(listOf(formValue))
        updateCartViewModel.getApiCall(request)
        updateCartViewModel.response.observe(viewLifecycleOwner) {response->
            response.onSuccess {
                Toast.makeText(requireContext(),"Cart value updated",Toast.LENGTH_LONG).show()
            }.onFailure {
                Toast.makeText(requireContext(),"Cart value update failed",Toast.LENGTH_LONG).show()
            }
        }
    }

    fun removeFromCart(position: Int, productId: String,productList:ArrayList<productCartItems>){
        val formValue = com.example.nopstationcart.Services.Model.Remove_Cart.FormValue("removefromcart",productId)
        val request = RemoveCartRequest(listOf(formValue))
        removeCartViewModel.getTheCartRemoved(request)
        removeCartViewModel.response.observe(viewLifecycleOwner){response->
            response.onSuccess {
                productList.removeAt(position)
                adapter.notifyItemRemoved(position)
                adapter.notifyItemRangeChanged(position,productList.size)
                Toast.makeText(requireContext(),"Cart Item is removed",Toast.LENGTH_LONG).show()
            }.onFailure {
                Toast.makeText(requireContext(),"Cart Item remove failed",Toast.LENGTH_LONG).show()
            }

        }
    }

}
