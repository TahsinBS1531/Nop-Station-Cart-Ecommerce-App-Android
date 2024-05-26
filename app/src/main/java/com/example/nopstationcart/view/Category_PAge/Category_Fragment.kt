package com.example.nopstationcart.view.Category_PAge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nopstationcart.R
import com.example.nopstationcart.dummyData.singleCategoryList
import com.example.nopstationcart.Services.Model.categoryDetailsItem
import com.example.nopstationcart.Services.Interfaces.onItemClickListener
import com.example.nopstationcart.Services.Model.CategoryList.CategorySingleItem
import com.example.nopstationcart.Services.Model.CategoryTree.categoryDataClass
import com.example.nopstationcart.databinding.FragmentCategoryBinding
import com.example.nopstationcart.view.Adapters.singleCategoryListAdapter
import com.example.nopstationcart.dummyData.dummyProductsList
import com.example.nopstationcart.view.Adapters.CategoryAdapter
import com.example.nopstationcart.view.Adapters.CategoryTreeAdapter
import com.example.nopstationcart.view.Home_Page.Home_PageDirections
import com.example.nopstationcart.viewmodel.CategoryTreeViewModel


class Category_Fragment : Fragment() {
    lateinit var binding:FragmentCategoryBinding
    private val categoryViewModels:CategoryTreeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_category_, container, false)
        binding = FragmentCategoryBinding.bind(view)

        categoryListRecycleView(view)
        return binding.root
    }

    private fun categoryListRecycleView(view: View?) {

        binding.categoryPageRecycle.layoutManager = GridLayoutManager(requireContext(),2)

        val categoryListApi = ArrayList<categoryDataClass>()

        val myAdapter = CategoryTreeAdapter(categoryListApi, object : CategoryTreeAdapter.OnItemClickListener {
            override fun onItemClick(category: categoryDataClass) {
//                val action = Home_PageDirections.actionHomePageToHomePageCategory(category.imageRes,category.products.toTypedArray(),category.tittle)
//                view?.findNavController()?.navigate(action)
            }

        })

        categoryViewModels.getCategoryList()
        categoryViewModels.result.observe(viewLifecycleOwner){result->
            categoryListApi.clear()
            result.onSuccess {
                it.Data.forEach {
                    val name = it.Name.toString()
                    val image = it.IconUrl
                    val id = it.CategoryId
                    val data = categoryDataClass(name,image,id)
                    categoryListApi.add(data)
                }
                myAdapter.notifyDataSetChanged()
            }.onFailure {
                Toast.makeText(requireContext(),"Category List Api call failed", Toast.LENGTH_LONG).show()
            }
            binding.categoryPageRecycle.adapter = myAdapter
        }

    }

}