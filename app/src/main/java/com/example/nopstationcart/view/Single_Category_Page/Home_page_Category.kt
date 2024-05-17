package com.example.nopstationcart.view.Single_Category_Page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nopstationcart.R
import com.example.nopstationcart.model.data.CategoryItem


class Home_page_Category : Fragment() {

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
            // Extract data from bundle
            val title = bundle.getString("Tittle")
            val imageResId = bundle.getInt("Img")
            val list = bundle.getParcelableArrayList<CategoryItem>("List")


            val textView = view.findViewById<TextView>(R.id.singleCategoryTittle)
            textView.text = title

            //Load image into an ImageView
            val imageView = view.findViewById<ImageView>(R.id.singleCategoryImg)
            imageView.setImageResource(imageResId)

            val recycle = view.findViewById<RecyclerView>(R.id.singleCategoryRecycle)

        }

        return view
    }

}