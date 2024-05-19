package com.example.nopstationcart.view.Category_PAge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nopstationcart.R
import com.example.nopstationcart.view.Adapters.singleCategoryListAdapter


class Category_Fragment : Fragment() {
    lateinit var recycleView:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_category_, container, false)
        recycleView = view.findViewById(R.id.categoryPageRecycle)
        recycleView.layoutManager = GridLayoutManager(requireContext(),2)
        val categoryArrayList = singleCategoryList().getProducts()
        recycleView.adapter = singleCategoryListAdapter(categoryArrayList)

        return view
    }

}