package com.example.nopstationcart.view.Product_Shopping_Cart

import android.content.Context
import android.content.SharedPreferences
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
import com.example.nopstationcart.Services.Netwrok.InternetStatus
import com.example.nopstationcart.databinding.FragmentProductCartMainBinding
import com.example.nopstationcart.dummyData.cartPrices
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
        handleCheckOutBtn()

        return binding.root
    }

    fun handleCheckOutBtn(){
        val sharedpreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val token = sharedpreferences.getString("TOKEN", null)
        binding.checkOutBtn.setOnClickListener {
            if (token==null){
                val editor = sharedpreferences.edit()
                editor.putString("User", "Guest")
                editor.apply()
                val action = Product_Cart_MainDirections.actionProductCartMainToLoginMain()
                findNavController().navigate(action)
            }else{
                val action = Product_Cart_MainDirections.actionProductCartMainToCheckoutMain()
                findNavController().navigate(action)
            }
        }
    }

    fun handleBackBtn(view:View){
        binding.backbtnAppBar.setOnClickListener {
            findNavController().popBackStack()
        }
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

        if (position < 0 || position >= productsList.size) {
            Toast.makeText(requireContext(), "Invalid position", Toast.LENGTH_LONG).show()
            return
        }

        if(quantity.toInt()==0){
            Toast.makeText(requireContext(),"Sorry You can't Decrease More",Toast.LENGTH_LONG).show()
        }else{
            val formValue = FormValue("itemquantity${productId}",quantity)
            val request = UpdateCartRequest(listOf(formValue))
            updateCartViewModel.getApiCall(request,requireContext())
            updateCartViewModel.response.observe(viewLifecycleOwner) {response->
                response.onSuccess {
                    Toast.makeText(requireContext(),"Cart value updated",Toast.LENGTH_LONG).show()
                    handlePrices(it.Data.OrderTotals.SubTotal,it.Data.OrderTotals.OrderTotal,it.Data.OrderTotals.Shipping)
                    //fetchDataAndUpdatePrices()
                }.onFailure {
                    Toast.makeText(requireContext(),"Cart value update failed",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun stopLoader(){
        binding.progressBarCart.visibility= View.GONE
    }
    private fun startLoader(){
        binding.progressBarCart.visibility= View.VISIBLE
    }

    fun removeFromCart(position: Int, productId: String,productList:ArrayList<productCartItems>){
        startLoader()
        val formValue = com.example.nopstationcart.Services.Model.Remove_Cart.FormValue("removefromcart",productId)
        val request = RemoveCartRequest(listOf(formValue))
        removeCartViewModel.getTheCartRemoved(request,requireContext())
        removeCartViewModel.response.observe(viewLifecycleOwner){response->
            response.onSuccess {removeResponse->
                stopLoader()
                val removedItem = productList.find { it.productId == productId.toInt() }
                if (removedItem != null) {
                    handlePrices(removeResponse.Data.OrderTotals.SubTotal,removeResponse.Data.OrderTotals.OrderTotal,removeResponse.Data.OrderTotals.Shipping)
                    binding.CartPageItems.text = "${removeResponse.Data.Cart.Items.size.toString()} Items"
                    binding.cartPageCartAmount.text =removeResponse.Data.Cart.Items.size.toString()

                    productList.remove(removedItem)
                    adapter.notifyDataSetChanged()
                    if (productList.isEmpty()) {
                        handlePrices("0", "0", "0")
                    }
                    Toast.makeText(requireContext(), "Cart Item is removed", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(requireContext(), "Failed to find item to remove", Toast.LENGTH_LONG).show()
                }
                //fetchDataAndUpdatePrices()
            }.onFailure {
                stopLoader()
                Toast.makeText(requireContext(),"Cart Item remove failed",Toast.LENGTH_LONG).show()
            }

        }
    }


    fun fetchData(){
        //for shimmer effect
        val ob = Home_Page()
        ob.startShimmer(binding.shimmerLayoutProductCart,binding.productCartRecycle)

        if(InternetStatus.isOnline(requireContext())){
            shoppingCartViewModel.getCartProducts(requireContext())
            shoppingCartViewModel.response.observe(viewLifecycleOwner) { result ->
                result.onSuccess { response ->
                    productsList.clear()
                    binding.cartPageCartAmount.text = response.Data.Cart.Items.size.toString()
                    binding.CartPageItems.text = "${response.Data.Cart.Items.size.toString()} Items"
                    if(response.Data.Cart.Items.size==0){
                        ob.stopShimmer(binding.shimmerLayoutProductCart,binding.productCartRecycle)
                        Toast.makeText(requireContext(),"There is no product in the cart",Toast.LENGTH_LONG).show()
                    }
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
                        cartPrices(subtotal,total,shippingPrice.toString())
                    }

                }.onFailure {
                    Toast.makeText(requireContext(), "Shopping cart Api call failed", Toast.LENGTH_LONG).show()
                }
            }
        }else{
            Toast.makeText(requireContext(), "No Internet Connection. Please Connect to a Network", Toast.LENGTH_LONG).show()
        }


    }

    fun handlePrices(subtotal:String, total:String, shipping:String){
        binding.CartPageSubTotal.text = subtotal
        binding.CartPageTotall.text = total
        binding.cartPageShiping.text =shipping
    }

}
