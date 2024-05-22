package com.example.nopstationcart.view.Single_Product_Page

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.example.nopstationcart.R
import com.example.nopstationcart.databinding.FragmentProductDeatilsBinding

class Product_Deatils : Fragment() {
    lateinit var Title: TextView
    lateinit var img:ImageView
    lateinit var price:TextView
    lateinit var backBtn : Toolbar
    lateinit var binding : FragmentProductDeatilsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product__deatils, container, false)

        val textView: TextView = view.findViewById(R.id.strikeThroughText)
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        getProductsDetails(view)
        handleBackBtn(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentProductDeatilsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    fun getProductsDetails(view:View){
        val args = Product_DeatilsArgs.fromBundle(requireArguments())
        val imageResId = args.productImage
        val itemTitle = args.productTittile
        val itemPrice = args.productPrice

        Title = view.findViewById(R.id.productPageTitle)
        img = view.findViewById(R.id.productPageImg)
        price = view.findViewById(R.id.productPagePrice)

        Title.text = itemTitle
        img.setImageResource(imageResId)
        price.text = itemPrice
    }

    fun handleBackBtn(view:View){
        backBtn = view.findViewById(R.id.product_details_back_btn)
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}