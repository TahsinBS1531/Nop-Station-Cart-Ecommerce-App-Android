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
import com.example.nopstationcart.Services.Model.ShoppingCart.productCartItems
import com.example.nopstationcart.databinding.FragmentProductCartMainBinding
import com.example.nopstationcart.view.Adapters.productCartAdapter
import com.example.nopstationcart.viewmodel.ShoppingCartViewModel

class Product_Cart_Main : Fragment() {
    private val shoppingCartViewModel: ShoppingCartViewModel by viewModels()
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
        adapter = productCartAdapter(productsList)
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
                    val item = productCartItems(tittle = title, imageResID = image, price = price, quantity = quantity)
                    productsList.add(item)

                    subTotal=it.SubTotalValue+subTotal
                }
                adapter.notifyDataSetChanged() // Notify adapter of data changes
                handlePrices(subTotal)
            }.onFailure {
                Toast.makeText(requireContext(), "Shopping cart Api call failed", Toast.LENGTH_LONG).show()
            }
        }
        println(subTotal)

        shoppingCartViewModel.getCartProducts()
        handleBackBtn(view)



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
}
