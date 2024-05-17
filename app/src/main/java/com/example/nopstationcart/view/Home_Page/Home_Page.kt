package com.example.nopstationcart.view.Home_Page

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.nopstationcart.view.Adapters.CategoryAdapter
import com.example.nopstationcart.R
import com.example.nopstationcart.model.data.CategoryItem
import com.example.nopstationcart.model.data.bestSellingItem
import com.example.nopstationcart.model.interfaces.onItemClickListener
import com.example.nopstationcart.view.Adapters.bestSellingAdapters
import com.example.nopstationcart.view.Adapters.featuredProductsAdapter
import com.example.nopstationcart.view.Adapters.womenHeelAdapter
import com.example.nopstationcart.view.MainActivity
import com.example.nopstationcart.view.Single_Category_Page.Home_page_Category
import com.example.nopstationcart.view.Single_Category_Page.foodCategorySingleitemList


class Home_Page : Fragment() {

    lateinit var myRecycleView:RecyclerView
    lateinit var myRecyclerView2: RecyclerView
    lateinit var myRecyclerView3: RecyclerView
    lateinit var myRecyclerView4: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.home_page_fragment, container, false)
        initializeImageSlider(view)
        categoryListRecycleView(view)
        bestSellingRecycleView(view)
        featuredRecycleView(view)
        womenHeelRecycleView(view)

        (activity as? MainActivity)?.setBottomNavigationVisibility(true)

        return view
    }

    private fun womenHeelRecycleView(view: View?) {
        if (view != null) {
            myRecyclerView4 = view.findViewById(R.id.women_heel_recycle)
        }
        myRecyclerView4.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val womenHeel = womenHeelProducts()
        val womenHeelArrayList = womenHeel.getProducts()
        myRecyclerView4.adapter = womenHeelAdapter(womenHeelArrayList)
    }

    private fun bestSellingRecycleView(view: View?) {
        if (view != null) {
            myRecyclerView2 = view.findViewById(R.id.bestSellingrecycleView)
        }
        myRecyclerView2.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val ob = bestSellingProducts()
        val bestSellingArrayList = ob.getProducts()
        myRecyclerView2.adapter = bestSellingAdapters(bestSellingArrayList)
    }

    private fun featuredRecycleView(view: View?) {
        if (view != null) {
            myRecyclerView3 = view.findViewById(R.id.featuredRecycle)
        }
        myRecyclerView3.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val featured = featuredProducts()
        val featuredArrayList = featured.getProducts()
        myRecyclerView3.adapter = featuredProductsAdapter(featuredArrayList)
    }

    private fun categoryListRecycleView(view: View?) {
        if (view != null) {
            myRecycleView = view.findViewById(R.id.categoryrecycleView)
        }
        myRecycleView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val category = categoryList()
        val categoryList = category.getProducts()
        val myAdapter = CategoryAdapter(categoryList)
        myRecycleView.adapter = myAdapter


        myAdapter.setOnItemClickListener(object :onItemClickListener{
            override fun onItemClick(position: Int) {
                val fragment = Home_page_Category()

                val bundle = Bundle()
                bundle.putString("Tittle",categoryList[position].text)
                bundle.putInt("Img",categoryList[position].imageResId)
                if(categoryList[position].text=="Food"){
                    val foodItems = foodCategorySingleitemList()
                    bundle.putParcelableArrayList("List",foodItems.getProducts())
                }
                fragment.arguments = bundle

                //on Clicking each item on the recycle view
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainer,fragment)
                //transaction.addToBackStack(null)
                transaction.commit()

            }

        })
    }

    private fun initializeImageSlider(view: View?) {
        val imageList = ArrayList<SlideModel>() // Create image list

        imageList.add(SlideModel(R.drawable.home_background, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.home_bg2, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.home_bg3, ScaleTypes.FIT))

        val imageSlider = view?.findViewById<ImageSlider>(R.id.image_slider)

        if (imageSlider != null) {
            imageSlider.setImageList(imageList)
            imageSlider.setSlideAnimation(AnimationTypes.ZOOM_OUT)
        }
    }


}