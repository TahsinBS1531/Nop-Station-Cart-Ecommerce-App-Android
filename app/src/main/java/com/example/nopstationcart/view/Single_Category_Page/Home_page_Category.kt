package com.example.nopstationcart.view.Single_Category_Page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nopstationcart.R
import com.example.nopstationcart.model.data.CategoryItem
import com.example.nopstationcart.model.data.categoryDetailsItem
import com.example.nopstationcart.model.interfaces.categoryDetailsOnClicklistener


class Home_page_Category : Fragment() {
    lateinit var backBtn: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.single_category_page, container, false)
        // Retrieve arguments bundle
        val bundle = arguments
        if (bundle != null) {
            val args = Home_page_CategoryArgs.fromBundle(requireArguments())
            val imageResId = args.productImage
            val items: List<categoryDetailsItem> = args.productList.toList()
            val title = args.productTittle


            val textView = view.findViewById<TextView>(R.id.singleCategoryTittle)
            textView.text = title

            val imageView = view.findViewById<ImageView>(R.id.singleCategoryImg)
            imageView.setImageResource(imageResId)

            val recycle = view.findViewById<RecyclerView>(R.id.singleCategoryRecycle)
            recycle.layoutManager = GridLayoutManager(requireContext(),2)
            val adapter = CategoryDetailsAdapter(items)
            recycle.adapter = adapter

            adapter.setOnItemClick(object : categoryDetailsOnClicklistener{
                override fun onItemClick(position: Int) {
                    val currentItem = items[position]
                    val title = currentItem.tittle
                    val imageResId = currentItem.imageResID
                    val itemPrice = currentItem.itemPrice
                    val action = Home_page_CategoryDirections.actionHomePageCategoryToProductDeatils(title,imageResId,itemPrice)
                    findNavController().navigate(action)
                }

                override fun onCartBtnClick(position: Int) {
                    TODO("Not yet implemented")
                }

            })


            handleBackBtn(view)

        }

        return view
    }

    fun handleBackBtn(view: View){
        backBtn = view.findViewById(R.id.categoryDetailsBackBtn)
        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}