package com.example.nopstationcart.view.Home_Page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.nopstationcart.view.Adapters.CategoryAdapter
import com.example.nopstationcart.R
import com.example.nopstationcart.model.interfaces.onItemClickListener
import com.example.nopstationcart.model.interfaces.womenHeelOnItemClickListener
import com.example.nopstationcart.view.Adapters.bestSellingAdapters
import com.example.nopstationcart.view.Adapters.featuredProductsAdapter
import com.example.nopstationcart.view.Adapters.womenHeelAdapter
import com.example.nopstationcart.view.Single_Category_Page.dummyProductsList


class Home_Page : Fragment(){

    lateinit var myRecycleView:RecyclerView
    lateinit var myRecyclerView2: RecyclerView
    lateinit var myRecyclerView3: RecyclerView
    lateinit var myRecyclerView4: RecyclerView
    lateinit var addToCartBtn:TextView
    var cartCount =0;
    //lateinit var navController:NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //navController = Navigation.findNavController(view)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.home_page_fragment, container, false)
        addToCartBtn = view.findViewById(R.id.home_page_cartBtn)
        initializeImageSlider(view)
        categoryListRecycleView(view)
        bestSellingRecycleView(view)
        featuredRecycleView(view)
        womenHeelRecycleView(view)

        //(activity as? MainActivity)?.setBottomNavigationVisibility(true)

        return view
    }
    private fun womenHeelRecycleView(view: View?){

        if (view != null) {
            myRecyclerView4 = view.findViewById(R.id.women_heel_recycle)
        }
        myRecyclerView4.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val womenHeel = womenHeelProducts()
        val womenHeelArrayList = womenHeel.getProducts()
        val adapter = womenHeelAdapter(womenHeelArrayList)
        myRecyclerView4.adapter = adapter

        adapter.setOnItemClick(object :womenHeelOnItemClickListener{
            override fun onItemClick(position: Int) {
                val itemTittle = womenHeelArrayList[position].tittle
                Toast.makeText(requireContext(),"This is a $itemTittle",Toast.LENGTH_LONG).show()
            }

            override fun onCartBtnClick(position: Int) {
                val itemTittle = womenHeelArrayList[position].tittle
                val itemPrice = womenHeelArrayList[position].price
                val itemImg = womenHeelArrayList[position].imgRes

                cartCount += 1
                addToCartBtn.text = cartCount.toString()
                Toast.makeText(requireContext(),"You clicked on a $itemTittle cart button",Toast.LENGTH_LONG).show()
//                val action = Home_PageDirections.actionHomePageToProductCartMain(itemTittle,itemPrice,itemImg)
//                findNavController().navigate(action)
            }

        })

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

                if(categoryList[position].text=="Food"){
                    val foodItems = dummyProductsList()
                    val items = foodItems.getProducts("Food")
                    val action = Home_PageDirections.actionHomePageToHomePageCategory(categoryList[position].imageResId,items.toTypedArray(),categoryList[position].text)
                    findNavController().navigate(action)
                }

                if(categoryList[position].text=="Furniture"){
                    val furnitureItems = dummyProductsList()
                    val items = furnitureItems.getProducts("Furniture")
                    val action = Home_PageDirections.actionHomePageToHomePageCategory(categoryList[position].imageResId,items.toTypedArray(),categoryList[position].text)
                    findNavController().navigate(action)
                }

                if(categoryList[position].text=="Phone"){
                    val phoneItems = dummyProductsList()
                    val items = phoneItems.getProducts("Phone")
                    val action = Home_PageDirections.actionHomePageToHomePageCategory(categoryList[position].imageResId,items.toTypedArray(),categoryList[position].text)
                    findNavController().navigate(action)
                }

                if(categoryList[position].text=="Watch"){
                    val phoneItems = dummyProductsList()
                    val items = phoneItems.getProducts("Watch")
                    val action = Home_PageDirections.actionHomePageToHomePageCategory(categoryList[position].imageResId,items.toTypedArray(),categoryList[position].text)
                    findNavController().navigate(action)
                }

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