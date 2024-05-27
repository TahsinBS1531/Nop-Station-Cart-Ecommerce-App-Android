package com.example.nopstationcart.view.Home_Page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.nopstationcart.view.Adapters.CategoryAdapter
import com.example.nopstationcart.R
import com.example.nopstationcart.dummyData.bestSellingProducts
import com.example.nopstationcart.dummyData.categoryList
import com.example.nopstationcart.dummyData.featuredProducts
import com.example.nopstationcart.dummyData.womenHeelProducts
import com.example.nopstationcart.Services.Interfaces.bestSellingProductsItemClick
import com.example.nopstationcart.Services.Interfaces.featuredProductsItemClickListener
import com.example.nopstationcart.Services.Interfaces.onItemClickListener
import com.example.nopstationcart.Services.Interfaces.womenHeelOnItemClickListener
import com.example.nopstationcart.Services.Model.CategoryList.CategorySingleItem
import com.example.nopstationcart.Services.Model.Home_Page.Featured_Products.featuredProductsItem2
import com.example.nopstationcart.Services.Model.featuredProductsItem
import com.example.nopstationcart.view.Adapters.bestSellingAdapters
import com.example.nopstationcart.view.Adapters.featuredProductsAdapter
import com.example.nopstationcart.view.Adapters.womenHeelAdapter
import com.example.nopstationcart.dummyData.dummyProductsList
import com.example.nopstationcart.viewmodel.CartViewModel
import com.example.nopstationcart.viewmodel.CategoryListViewModel
import com.example.nopstationcart.viewmodel.FeaturedProductsViewModel
import com.example.nopstationcart.viewmodel.LoginViewModel
import com.example.nopstationcart.viewmodel.SliderViewModel


class Home_Page : Fragment(){

    lateinit var myRecycleView:RecyclerView
    lateinit var myRecyclerView2: RecyclerView
    lateinit var myRecyclerView3: RecyclerView
    lateinit var myRecyclerView4: RecyclerView
    lateinit var addToCartBtn:TextView
    lateinit var logOutBtn:TextView
    var cartCount =0;
    private val sliderViewModel: SliderViewModel by viewModels()
    private val featuredViewModel: FeaturedProductsViewModel by viewModels()
    private val categoryListViewModel: CategoryListViewModel by viewModels()
    private val cartPageViewModel: CartViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_page_fragment, container, false)
        addToCartBtn = view.findViewById(R.id.home_page_cartBtn)
        initializeImageSlider(view)
        categoryListRecycleView(view)
        bestSellingRecycleView(view)
        featuredRecycleView(view)
        womenHeelRecycleView(view)

        handleLogOut(view)

        return view
    }
    fun handleLogOut(view: View?){

        if (view != null) {
            logOutBtn = view.findViewById(R.id.logOutBtn)
        }
        logOutBtn.setOnClickListener {
            //val handler = logOutHandler(requireContext())
            //handler.getLogOut()
        }
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

        adapter.setOnItemClick(object : womenHeelOnItemClickListener {
            override fun onItemClick(position: Int) {
                val itemTittle = womenHeelArrayList[position].tittle.toString()
                val itemImg = womenHeelArrayList[position].imgRes
                val itemPrice = womenHeelArrayList[position].price.toString()
                Toast.makeText(requireContext(),"This is a $itemTittle",Toast.LENGTH_LONG).show()

                val action = Home_PageDirections.actionHomePageToProductDeatils(itemTittle,"https://images.pexels.com/photos/24280095/pexels-photo-24280095/free-photo-of-an-armchair-and-a-basket-of-flowers-standing-in-a-garden.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",itemPrice,"Dummy Data for now", "Dummy Data for now","20.00$")
                findNavController().navigate(action)
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
        val adapter = bestSellingAdapters(bestSellingArrayList)
        myRecyclerView2.adapter = adapter
        adapter.setOnItemClick(object : bestSellingProductsItemClick {
            override fun onItemClick(position: Int) {
                val currentItem = bestSellingArrayList[position]
                val itemImg = currentItem.imageRes
                val itemTitle = currentItem.tittle
                val itemPrice = currentItem.price

                val action = Home_PageDirections.actionHomePageToProductDeatils(itemTitle,"https://images.pexels.com/photos/24280095/pexels-photo-24280095/free-photo-of-an-armchair-and-a-basket-of-flowers-standing-in-a-garden.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",itemPrice,"Dummy Data for now", "Dummy Data for now","20.00$")
                findNavController().navigate(action)
            }

            override fun onCartBtnClick(position: Int) {
                TODO("Not yet implemented")
            }

        })


    }

    private fun featuredRecycleView(view: View?) {
        if (view != null) {
            myRecyclerView3 = view.findViewById(R.id.featuredRecycle)
        }
        myRecyclerView3.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        featuredViewModel.getFeaturedProducts()
        var featuredList = arrayListOf<featuredProductsItem2>()
        val adapter = featuredProductsAdapter(featuredList)
        featuredViewModel.result.observe(viewLifecycleOwner){ it ->
            it.onSuccess {response ->
                response.Data.forEach {
                    val name = it.Name
                    val price = it.ProductPrice.Price.toString()?:"0.0"
                    val image = it.PictureModels[0].ImageUrl?:"No Image Found"
                    var rating = 0f
                    val shortDes = it.ShortDescription
                    val longDes = it.FullDescription
                    val oldPrice = it.ProductPrice.OldPrice?:"0.0"
                    val id = it.Id
                    if(it.ReviewOverviewModel.TotalReviews !=0){
                        rating = (it.ReviewOverviewModel.RatingSum/it.ReviewOverviewModel.TotalReviews).toFloat()
                    }
                    val data = featuredProductsItem2(name,price, image = image,rating,shortDes,longDes,oldPrice, id = id)
                    featuredList.add(data)
                }

                //val adapter = featuredProductsAdapter(featuredList)
                myRecyclerView3.adapter = adapter

            }.onFailure {
                Toast.makeText(requireContext(),"Image data Api call failed",Toast.LENGTH_LONG).show()
            }
        }


        adapter.setOnItemClick(object: featuredProductsItemClickListener {
            override fun onItemClick(position: Int) {
                val currentItem = featuredList[position]
                val image = currentItem.image
                val title = currentItem.tittle
                val shortDes = currentItem.shortDes
                val longDes = currentItem.longDes
                val price = currentItem.price
                val oldPrice = currentItem.oldPrice

                Toast.makeText(requireContext(),"This is a $title",Toast.LENGTH_LONG).show()
                val action = Home_PageDirections.actionHomePageToProductDeatils(title,image,price,shortDes, longDes,oldPrice)
                findNavController().navigate(action)

            }

            override fun onCartBtnClick(position: Int) {
                val currentItem = featuredList[position]
                val productId = currentItem.id
                cartPageViewModel.getCartResponse(productId)
                cartPageViewModel.result.observe(viewLifecycleOwner){
                    it.onSuccess {response->
                        Toast.makeText(requireContext(),"${response.Message}",Toast.LENGTH_LONG).show()
                    }.onFailure {response->
                        Toast.makeText(requireContext(),"${response.cause?.cause}",Toast.LENGTH_LONG).show()
                        println(response.cause?.message)
                    }
                }
                println(productId)
                println(currentItem.tittle)


            }

        })
    }

    private fun categoryListRecycleView(view: View?) {
        if (view != null) {
            myRecycleView = view.findViewById(R.id.categoryrecycleView)
        }
        myRecycleView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        val categoryListApi = ArrayList<CategorySingleItem>()

        val myAdapter = CategoryAdapter(categoryListApi, object : CategoryAdapter.OnItemClickListener {
            override fun onItemClick(category: CategorySingleItem) {
                val action = Home_PageDirections.actionHomePageToHomePageCategory(category.imageRes,category.products.toTypedArray(),category.tittle)
                view?.findNavController()?.navigate(action)
            }
        })

        categoryListViewModel.getCategoryList()
        categoryListViewModel.result.observe(viewLifecycleOwner){result->
            categoryListApi.clear()
            result.onSuccess {
                it.Data.forEach {
                    val name = it.Name.toString()
                    val image = it.Products[0].PictureModels[0].FullSizeImageUrl
                    val id = it.Id
                    val products = it.Products
                    val data = CategorySingleItem(id,name,image,products)
                    categoryListApi.add(data)
                }
                myAdapter.notifyDataSetChanged()
            }.onFailure {
                Toast.makeText(requireContext(),"Category List Api call failed",Toast.LENGTH_LONG).show()
            }
            myRecycleView.adapter = myAdapter
        }

    }

    private fun initializeImageSlider(view: View?) {
        val imageList = ArrayList<SlideModel>() // Create image list
        sliderViewModel.getSlider()
        sliderViewModel.sliderResult.observe(viewLifecycleOwner){
            it.onSuccess {response ->
                response.Data.Sliders.forEach {slider->
                    imageList.add(SlideModel(slider.ImageUrl,ScaleTypes.FIT))
                }
                view?.findViewById<ImageSlider>(R.id.image_slider)?.apply {
                    setImageList(imageList)
                    setSlideAnimation(AnimationTypes.ZOOM_OUT)
                }
            }.onFailure {
                Toast.makeText(requireContext(),"Failed to load images", Toast.LENGTH_LONG).show()
            }
        }

    }


}