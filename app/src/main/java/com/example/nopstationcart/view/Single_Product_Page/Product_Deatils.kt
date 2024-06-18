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
import com.example.nopstationcart.view.Product_Shopping_Cart.CartViewModel
import com.example.nopstationcart.view.Product_Shopping_Cart.ShoppingCartViewModel

class Product_Deatils : Fragment() {
    lateinit var binding : FragmentProductDeatilsBinding
    private val cartPageViewModel: CartViewModel by viewModels()
    private val shoppingCartViewModel: ShoppingCartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product__deatils, container, false)
        binding = FragmentProductDeatilsBinding.bind(view)
        binding.productPageOldPrice.paintFlags = binding.productPageOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
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
        val oldPrice = args.oldPrice
        val productId = args.productId
        val shortDes = args.shortDescription
        val longDes = args.fullDescription

        binding.productPageTitle.text = itemTitle
        binding.productPageOldPrice.text = oldPrice
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

    private var cartAmount = 1

    fun handleCartBtn(id: String) {
        binding?.apply {
            ProductDetailsAddToCart.setOnClickListener {
                if(InternetStatus.isOnline(requireContext())){
                    println("Cart Amount From the Details page : $cartAmount")
                    cartPageViewModel.getCartResponse(id.toInt(), requireContext(), cartAmount.toString())
                    cartPageViewModel.result.observe(viewLifecycleOwner) { response ->
                        response.onSuccess {
                            handleCartAmount()
                            println("Total Cart Items: ${it.Data.TotalShoppingCartProducts}")
                            println("Amount: $cartAmount")
                            Toast.makeText(requireContext(), it.Message, Toast.LENGTH_LONG).show()
                        }.onFailure {
                            Toast.makeText(requireContext(), "${it.cause?.cause}", Toast.LENGTH_LONG).show()
                            println(it.cause?.message)
                        }
                    }
                }else{
                    Toast.makeText(requireContext(), "No Internet Connection. Please Connect to a Network", Toast.LENGTH_LONG).show()
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