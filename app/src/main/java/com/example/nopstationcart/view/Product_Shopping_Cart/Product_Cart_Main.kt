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
import com.example.nopstationcart.view.Home_Page.Home_Page
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

        binding.productCartRecycle.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = productCartAdapter(productsList,removeCartViewModel)
        binding.productCartRecycle.adapter = adapter

        fetchData()
        handleBackBtn(view)
        handleCartBtn(adapter,productsList)

        return binding.root
    }

    fun handleBackBtn(view:View){
        binding.backbtnAppBar.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun handlePrices(subtotal:String, total:String, shipping:String){
        binding.CartPageSubTotal.text = subtotal
        binding.CartPageTotall.text = total
        binding.cartPageShiping.text =shipping
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
        updateCartViewModel.getApiCall(request,requireContext())
        updateCartViewModel.response.observe(viewLifecycleOwner) {response->
            response.onSuccess {
                Toast.makeText(requireContext(),"Cart value updated",Toast.LENGTH_LONG).show()
                binding.CartPageSubTotal.text = it.Data.OrderTotals.SubTotal
                binding.CartPageTotall.text = it.Data.OrderTotals.OrderTotal
                binding.cartPageShiping.text = it.Data.OrderTotals.Shipping
                if(quantity.toInt() ==0){
                    productsList.removeAt(position)
                    adapter.notifyItemRemoved(position)
                    adapter.notifyItemRangeChanged(position,productsList.size)
                }
                //fetchDataAndUpdatePrices()
            }.onFailure {
                Toast.makeText(requireContext(),"Cart value update failed",Toast.LENGTH_LONG).show()
            }
        }
    }

    fun removeFromCart(position: Int, productId: String,productList:ArrayList<productCartItems>){
        val formValue = com.example.nopstationcart.Services.Model.Remove_Cart.FormValue("removefromcart",productId)
        val request = RemoveCartRequest(listOf(formValue))
        removeCartViewModel.getTheCartRemoved(request,requireContext())
        removeCartViewModel.response.observe(viewLifecycleOwner){response->
            response.onSuccess {
                if(position>=0 && position <productList.size){
                    Toast.makeText(requireContext(),"Cart Item is removed",Toast.LENGTH_LONG).show()
                    binding.CartPageSubTotal.text=it.Data.OrderTotals.SubTotal
                    binding.CartPageTotall.text = it.Data.OrderTotals.OrderTotal
                    binding.cartPageShiping.text = it.Data.OrderTotals.Shipping
                    productList.removeAt(position)
                    adapter.notifyItemRemoved(position)
                    adapter.notifyItemRangeChanged(position,productList.size)
                }

                //fetchDataAndUpdatePrices()
            }.onFailure {
                Toast.makeText(requireContext(),"Cart Item remove failed",Toast.LENGTH_LONG).show()
            }

        }
    }


    fun fetchData(){
        //for shimmer effect
        val ob = Home_Page()
        ob.startShimmer(binding.shimmerLayoutProductCart,binding.productCartRecycle)

        shoppingCartViewModel.getCartProducts(requireContext())
        shoppingCartViewModel.response.observe(viewLifecycleOwner) { result ->
            result.onSuccess { response ->
                productsList.clear() // Clear the list to avoid duplicates
                response.Data.Cart.Items.forEach {
                    val title = it.ProductName
                    val image = it.Picture.ImageUrl
                    val price = it.UnitPrice
                    val quantity = it.Quantity
                    val productId = it.Id
                    println(quantity)
                    val item = productCartItems(tittle = title, imageResID = image, price = price, quantity = quantity,
                        productId = productId
                    )
                    productsList.add(item)
                    //stopping shimmer
                    ob.stopShimmer(binding.shimmerLayoutProductCart,binding.productCartRecycle)

                }
                adapter.notifyDataSetChanged() // Notify adapter of data changes
                val total:String? = response.Data.OrderTotals.OrderTotal?:"0"
                val subtotal:String? = response.Data.OrderTotals.SubTotal?:"0"
                val shippingPrice:String? = response.Data.OrderTotals.Shipping.toString()?:"0"
                if (subtotal != null && total !=null) {
                    handlePrices(subtotal,total,shippingPrice.toString())
                }

            }.onFailure {
                Toast.makeText(requireContext(), "Shopping cart Api call failed", Toast.LENGTH_LONG).show()
            }
        }


    }

}
