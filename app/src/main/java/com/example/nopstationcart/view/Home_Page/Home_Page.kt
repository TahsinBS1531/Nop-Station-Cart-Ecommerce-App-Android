package com.example.nopstationcart.view.Home_Page

import android.content.Context
import android.content.res.loader.ResourcesLoader
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.Resource
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.nopstationcart.view.Adapters.CategoryAdapter
import com.example.nopstationcart.R
import com.example.nopstationcart.Services.Interfaces.ItemClickListener
import com.example.nopstationcart.dummyData.bestSellingProducts
import com.example.nopstationcart.dummyData.womenHeelProducts
import com.example.nopstationcart.Services.Interfaces.bestSellingProductsItemClick
import com.example.nopstationcart.Services.Interfaces.womenHeelOnItemClickListener
import com.example.nopstationcart.Services.Model.CategoryList.CategorySingleItem
import com.example.nopstationcart.Services.Model.Home_Page.Featured_Products.featuredProductsItem2
import com.example.nopstationcart.databinding.HomePageFragmentBinding
import com.example.nopstationcart.view.Adapters.bestSellingAdapters
import com.example.nopstationcart.view.Adapters.featuredProductsAdapter
import com.example.nopstationcart.view.Adapters.womenHeelAdapter
import com.example.nopstationcart.viewmodel.CartViewModel
import com.example.nopstationcart.viewmodel.CategoryListViewModel
import com.example.nopstationcart.viewmodel.FeaturedProductsViewModel
import com.example.nopstationcart.viewmodel.LogOutViewModel
import com.example.nopstationcart.viewmodel.SliderViewModel
import com.facebook.shimmer.ShimmerFrameLayout


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
    private val logOutViewModel:LogOutViewModel by viewModels()
    lateinit var totallCartProducts:String
    private lateinit var binding:HomePageFragmentBinding
    private var featuredList = mutableListOf<featuredProductsItem2>()
    private lateinit var featuredAdapter: featuredProductsAdapter


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
        binding = HomePageFragmentBinding.bind(view)

        addToCartBtn = view.findViewById(R.id.home_page_cartBtn)

        binding.cartBtn.setOnClickListener {
            val action = Home_PageDirections.actionHomePageToProductCartMain()
            findNavController().navigate(action)
        }

        initializeImageSlider(view)
        categoryListRecycleView(view)
        bestSellingRecycleView(view)
        featuredRecycleView(view)
        womenHeelRecycleView(view)

        handleLogOut(view)

        return binding.root
    }
    fun handleLogOut(view: View?){
        binding.logOutBtn.setOnClickListener {
            logOutViewModel.getCartResponse(requireContext())
            logOutViewModel.result.observe(viewLifecycleOwner){response->
                response.onSuccess {
                    val sharedpreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
                    with(sharedpreferences.edit()) {
                        remove("TOKEN")
                        apply()
                    }
                    Toast.makeText(requireContext(),"Log Out Successful",Toast.LENGTH_LONG).show()
                    val action = Home_PageDirections.actionHomePageToLoginMain()
                    findNavController().navigate(action)
                }.onFailure {
                    Toast.makeText(requireContext(),"Log Out failed",Toast.LENGTH_LONG).show()
                }
            }
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
                val productId = 1
                Toast.makeText(requireContext(),"This is a $itemTittle",Toast.LENGTH_LONG).show()

                val action = Home_PageDirections.actionHomePageToProductDeatils(itemTittle,"https://images.pexels.com/photos/24280095/pexels-photo-24280095/free-photo-of-an-armchair-and-a-basket-of-flowers-standing-in-a-garden.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",itemPrice,"Dummy Data for now", "Dummy Data for now","20.00$", productId = productId.toString())
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

                val action = Home_PageDirections.actionHomePageToProductDeatils(itemTitle,"https://images.pexels.com/photos/24280095/pexels-photo-24280095/free-photo-of-an-armchair-and-a-basket-of-flowers-standing-in-a-garden.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",itemPrice,"Dummy Data for now", "Dummy Data for now","20.00$","1")
                findNavController().navigate(action)
            }

            override fun onCartBtnClick(position: Int) {
                TODO("Not yet implemented")
            }

        })


    }

    private fun featuredRecycleView(view: View?) {
        startShimmer(binding.shimmerLayoutFeatured,binding.featuredRecycle)

        if (view != null) {
            myRecyclerView3 = view.findViewById(R.id.featuredRecycle)
        }
        myRecyclerView3.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        featuredViewModel.getFeaturedProducts()
        var featuredList = arrayListOf<featuredProductsItem2>()
        val adapter = featuredProductsAdapter(featuredList)
        myRecyclerView3.adapter = adapter
        featuredViewModel.result.observe(viewLifecycleOwner){ it ->
            featuredList.clear()
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
                adapter.notifyDataSetChanged()
                stopShimmer(binding.shimmerLayoutFeatured,binding.featuredRecycle)
                //val adapter = featuredProductsAdapter(featuredList)
                //myRecyclerView3.adapter = adapter

            }.onFailure {
                Toast.makeText(requireContext(),"Image data Api call failed",Toast.LENGTH_LONG).show()
            }
        }


        adapter.setOnItemClick(object: ItemClickListener {
            override fun onItemClick(position: Int) {
                val currentItem = featuredList[position]
                val image = currentItem.image
                val title = currentItem.tittle
                val shortDes = currentItem.shortDes
                val longDes = currentItem.longDes
                val price = currentItem.price
                val oldPrice = currentItem.oldPrice
                val productId = currentItem.id

                Toast.makeText(requireContext(),"This is a $title",Toast.LENGTH_LONG).show()
                val action = Home_PageDirections.actionHomePageToProductDeatils(title,image,price,shortDes, longDes,oldPrice,productId.toString())
                findNavController().navigate(action)

            }

            override fun onCartBtnClick(position: Int) {
                val currentItem = featuredList[position]
                val productId = currentItem.id
                cartPageViewModel.getCartResponse(productId,requireContext())
                cartPageViewModel.result.observe(viewLifecycleOwner){
                    it.onSuccess {response->
                        totallCartProducts = response.Data.TotalShoppingCartProducts.toString()
                        Toast.makeText(requireContext(),"${response.Message}",Toast.LENGTH_LONG).show()
                        setShoppingCart(totallCartProducts)
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
     fun startShimmer(shimmer: ShimmerFrameLayout,view : RecyclerView){
        shimmer.startShimmer()
        shimmer.visibility = View.VISIBLE
        view.visibility = View.GONE
    }

     fun stopShimmer(shimmer: ShimmerFrameLayout,view : RecyclerView){
        shimmer.stopShimmer()
        shimmer.visibility= View.GONE
        view.visibility =View.VISIBLE
    }

    private fun categoryListRecycleView(view: View?) {
        startShimmer(binding.shimmerLayoutCategories,binding.categoryrecycleView)

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
                stopShimmer(binding.shimmerLayoutCategories,binding.categoryrecycleView)
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

    private fun setShoppingCart(quantity:String){
        binding.homePageCartBtn.text = quantity
    }


}