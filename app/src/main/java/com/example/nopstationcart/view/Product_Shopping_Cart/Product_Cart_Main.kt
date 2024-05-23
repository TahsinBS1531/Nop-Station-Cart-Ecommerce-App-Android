package com.example.nopstationcart.view.Product_Shopping_Cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nopstationcart.R
import com.example.nopstationcart.Services.Model.productCartItems
import com.example.nopstationcart.view.Adapters.productCartAdapter


class Product_Cart_Main : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product__cart__main, container, false)

        val args = Product_Cart_MainArgs.fromBundle(requireArguments())
        val imageResId = args.image
        val tittle = args.tittle
        val price = args.price


        val recycle = view.findViewById<RecyclerView>(R.id.productCartRecycle)
        recycle.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        val items = productCartItems(tittle,price,imageResId)
        val itemList = listOf(items)
        val adapter = productCartAdapter(itemList)
        recycle.adapter = adapter


        return view
    }
}