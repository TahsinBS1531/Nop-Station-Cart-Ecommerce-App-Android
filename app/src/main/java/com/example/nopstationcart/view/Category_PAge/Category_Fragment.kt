package com.example.nopstationcart.view.Category_PAge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nopstationcart.R
import com.example.nopstationcart.dummyData.singleCategoryList
import com.example.nopstationcart.Services.Model.categoryDetailsItem
import com.example.nopstationcart.Services.Interfaces.onItemClickListener
import com.example.nopstationcart.view.Adapters.singleCategoryListAdapter
import com.example.nopstationcart.dummyData.dummyProductsList


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
        val adapter = singleCategoryListAdapter(categoryArrayList)
        recycleView.adapter = adapter
        adapter.OnItemClick(object : onItemClickListener {
            override fun onItemClick(position: Int) {
                val currentItem = categoryArrayList[position]
                val title = currentItem.text
                val imageRes = currentItem.imageRes
                val items = dummyProductsList()
                var itemsList = ArrayList<categoryDetailsItem>()
                when(title){
                    "Food" ->  {
                        itemsList = getTitles("Food",items)
                    }
                    "Furniture" -> {
                        itemsList= getTitles("Furniture",items)
                    }
                    "Phone" -> {
                        itemsList = getTitles("Phone",items)
                    }
                    "Watch" -> {
                        itemsList = getTitles("Watch",items)
                    }
                    else -> itemsList = getTitles("Food",items)
                }

                val action = Category_FragmentDirections.actionCategoryFragmentToHomePageCategory(imageRes,itemsList.toTypedArray(),title)
                findNavController().navigate(action)

            }

        }
        )


        return view
    }

    fun getTitles(title:String, items: dummyProductsList): ArrayList<categoryDetailsItem> {
        val itemsList = items.getProducts(title)
        return itemsList

    }

}