package com.example.nopstationcart.view.Single_Product_Page

import android.graphics.Paint
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.nopstationcart.R
import com.example.nopstationcart.Services.Netwrok.InternetStatus
import com.example.nopstationcart.databinding.FragmentProductDeatilsBinding
import com.example.nopstationcart.utils.NetworkResult
import com.example.nopstationcart.view.Product_Shopping_Cart.CartViewModel
import com.example.nopstationcart.view.Product_Shopping_Cart.ShoppingCartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Product_Deatils : Fragment() {
    lateinit var binding : FragmentProductDeatilsBinding
    private val cartPageViewModel: CartViewModel by viewModels()
    private val shoppingCartViewModel: ShoppingCartViewModel by viewModels()
    var flag:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product__deatils, container, false)
        binding = FragmentProductDeatilsBinding.bind(view)
        getProductsDetails(view)
        handleBackBtn(view)
        handleCartAmount()

        binding.productDetailsCart.setOnClickListener {
            val action = Product_DeatilsDirections.actionProductDeatilsToProductCartMain()
            findNavController().navigate(action)
        }

        return binding.root
    }

    fun handleCartAmount(){
        shoppingCartViewModel.getCartProducts(requireContext())
        shoppingCartViewModel.response.observe(viewLifecycleOwner) { result ->
            result.onSuccess { response ->
                val cartAmount = response.Data.Cart.Items.size.toString()
                println("Cart Amount: $cartAmount")
                binding.productDeatilsCartAmount.text = cartAmount
            }.onFailure {
                val cartAmount = "0"
                binding.productDeatilsCartAmount.text = cartAmount
            }
        }

    }
    fun getProductsDetails(view:View){
        val args = Product_DeatilsArgs.fromBundle(requireArguments())
        val imageResId = args.productImage
        val itemTitle = args.productTittile
        val itemPrice = args.productPrice
        val oldPrice = args.oldPrice.toFloat()
        val productId = args.productId
        val shortDes = args.shortDescription
        val longDes = args.fullDescription

        binding.productPageTitle.text = itemTitle

        val formattedOldPrice = String.format("$%.2f", oldPrice)
        binding.productPageOldPrice.text = formattedOldPrice
        binding.productPageOldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        binding.productPagePrice.text = itemPrice

        binding.productPageShortDes.text = Html.fromHtml(shortDes, Html.FROM_HTML_MODE_LEGACY)
        binding.productPageLongDes.text = Html.fromHtml(longDes, Html.FROM_HTML_MODE_LEGACY)

        Glide.with(this)
            .load(imageResId)
            .into(binding.productPageImg)

        handleCartBtn(productId)

    }

    fun handleBackBtn(view:View){
        binding.productDetailsBackBtn.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    private fun initObserver(){
        cartPageViewModel.responseLiveData.observe(viewLifecycleOwner){result->
            when(result){
                is NetworkResult.Loading ->{
                    println("Loading Data")
                }
                is NetworkResult.Error -> if(flag){
                    Toast.makeText(requireContext(),"Error on adding the cart",Toast.LENGTH_SHORT).show()
                    flag =false
                }
                is NetworkResult.Success -> {
                    if(flag){
                        Toast.makeText(requireContext(),"Item is added on the cart",Toast.LENGTH_SHORT).show()
                        flag=false
                    }
                    handleCartAmount()
                }
            }


        }
    }

    private var cartAmount = 1

    private fun handleCartBtn(id: String) {
        binding.apply {
            ProductDetailsAddToCart.setOnClickListener {
                if(InternetStatus.isOnline(requireContext())){
                    cartPageViewModel.addToCart(id.toInt(),cartAmount.toString())
                    flag = true
                }else{
                    Toast.makeText(requireContext(),"No Internet Connection. Please Connect to a network.",Toast.LENGTH_SHORT).show()
                }

            }

            productDetailsIncreaseCart.setOnClickListener {
                cartAmount++
                editText.setText(cartAmount.toString())
                println("Cart Increase btn is called $cartAmount")
            }

            productDetailsDecreaseCart.setOnClickListener {
                println("Cart Decrease btn is called")
                if (cartAmount > 1) {
                    cartAmount--
                    editText.setText(cartAmount.toString())
                } else {
                    Toast.makeText(requireContext(), "Sorry, you can't decrease more", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}