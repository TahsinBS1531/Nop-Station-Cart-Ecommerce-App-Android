package com.example.nopstationcart.view.Single_Product_Page

import android.graphics.Paint
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.nopstationcart.R
import com.example.nopstationcart.databinding.FragmentProductDeatilsBinding

class Product_Deatils : Fragment() {
    lateinit var binding : FragmentProductDeatilsBinding

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

        return binding.root
    }


    fun getProductsDetails(view:View){
        val args = Product_DeatilsArgs.fromBundle(requireArguments())
        val imageResId = args.productImage
        val itemTitle = args.productTittile
        val itemPrice = args.productPrice
        val oldPrice = args.oldPrice
        val shortDes = args.shortDescription
        val longDes = args.fullDescription


        binding.productPageTitle.text = itemTitle
        binding.productPageOldPrice.text = oldPrice
        binding.productPagePrice.text = itemPrice

        binding.productPageShortDes.text = Html.fromHtml(binding.productPageShortDes.text.toString(), Html.FROM_HTML_MODE_LEGACY)
        binding.productPageLongDes.text = Html.fromHtml(binding.productPageLongDes.text.toString(), Html.FROM_HTML_MODE_LEGACY)

        Glide.with(this)
            .load(imageResId)
            .into(binding.productPageImg)

    }

    fun handleBackBtn(view:View){
        binding.productDetailsBackBtn.setOnClickListener{
            findNavController().popBackStack()
        }
    }

}