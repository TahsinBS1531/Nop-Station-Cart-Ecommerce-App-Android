package com.example.nopstationcart.view.Single_Product_Page

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.nopstationcart.R

class Product_Deatils : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product__deatils, container, false)

        val textView: TextView = view.findViewById(R.id.strikeThroughText)
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        return view
    }

}