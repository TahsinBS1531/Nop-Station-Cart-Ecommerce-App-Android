package com.example.nopstationcart.view.Home_Page

import android.content.Context
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
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.nopstationcart.view.Adapters.CategoryAdapter
import com.example.nopstationcart.R
import com.example.nopstationcart.Services.Interfaces.ItemClickListener
import com.example.nopstationcart.Services.Local.AppDatabase
import com.example.nopstationcart.Services.Model.CategoryList.CategorySingleItem
import com.example.nopstationcart.Services.Model.Home_Page.Featured_Products.featuredProductsItem2
import com.example.nopstationcart.Services.Netwrok.InternetStatus
import com.example.nopstationcart.databinding.HomePageFragmentBinding
import com.example.nopstationcart.view.Adapters.featuredProductsAdapter
import com.example.nopstationcart.viewmodel.CartViewModel
import com.example.nopstationcart.viewmodel.CategoryListViewModel
import com.example.nopstationcart.viewmodel.FeaturedProductsViewModel
import com.example.nopstationcart.viewmodel.LogOutViewModel
import com.example.nopstationcart.viewmodel.ShoppingCartViewModel
import com.example.nopstationcart.viewmodel.SliderViewModel
import com.facebook.shimmer.ShimmerFrameLayout

class Home_Page : Fragment(){

    lateinit var myRecycleView:RecyclerView
    lateinit var myRecyclerView2: RecyclerView
    lateinit var myRecyclerView3: RecyclerView
    lateinit var myRecyclerView4: RecyclerView
    lateinit var addToCartBtn:TextView
    var cartCount =0;
    private val sliderViewModel: SliderViewModel by viewModels()
    private val featuredViewModel: FeaturedProductsViewModel by viewModels()
    private val categoryListViewModel: CategoryListViewModel by viewModels()
    private val cartPageViewModel: CartViewModel by viewModels()
    private val logOutViewModel:LogOutViewModel by viewModels()
    private val shoppingCartViewModel:ShoppingCartViewModel by viewModels()
    lateinit var totallCartProducts:String
    private lateinit var binding:HomePageFragmentBinding


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
        initializeImageSlider(view)
        categoryListRecycleView(view)
        //bestSellingRecycleView(view)
        featuredRecycleView(view)
        //womenHeelRecycleView(view)
        setShoppingCart()
        handleCartBtn()
        val sharedpreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

        val token = sharedpreferences.getString("TOKEN", null)
        if(token==null){
            binding.logOutBtn.visibility = View.GONE
        }else{
            binding.logOutBtn.visibility = View.VISIBLE
        }
        handleLogOut(view)

        return binding.root
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

    fun handleCartBtn(){
        binding.cartBtn.setOnClickListener {
            val action = Home_PageDirections.actionHomePageToProductCartMain()
            findNavController().navigate(action)
        }
    }
    fun handleLogOut(view: View?) {
        binding.logOutBtn.setOnClickListener {
            logOutViewModel.getCartResponse(requireContext())
            logOutViewModel.result.observe(viewLifecycleOwner) { response ->
                response.onSuccess {
                    val sharedpreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
                    with(sharedpreferences.edit()) {
                        remove("TOKEN")
                        remove("Email")
                        apply()
                    }
                    val token = sharedpreferences.getString("TOKEN", null)
                    val email = sharedpreferences.getString("Email", null)
                    println("Token after successful log out: $token")
                    println("Email after successful log out: $email")
                    Toast.makeText(requireContext(), "Log Out Successful", Toast.LENGTH_LONG).show()
                    navigateToLogin()
                }.onFailure {
                    Toast.makeText(requireContext(), "Log Out failed", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun navigateToLogin() {
        val action = Home_PageDirections.actionHomePageToLoginMain()
        findNavController().navigate(action)
    }



    private fun featuredRecycleView(view: View?) {
        startShimmer(binding.shimmerLayoutFeatured,binding.featuredRecycle)

        if (view != null) {
            myRecyclerView3 = view.findViewById(R.id.featuredRecycle)
        }
        myRecyclerView3.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        featuredViewModel.getFeaturedProducts(requireContext())
        var featuredList = arrayListOf<featuredProductsItem2>()
        val adapter = featuredProductsAdapter(featuredList)
        myRecyclerView3.adapter = adapter
        featuredViewModel.featuredProductsResult.observe(viewLifecycleOwner){ it ->
            featuredList.clear()
            it.onSuccess {response ->
                response.forEach {
                    val name = it.name
                    val price = it.price?:"0.0"
                    val image = it.imageUrl?:"No Image Found"
                    var rating = 0f
                    val shortDes = it.shortDescription
                    val longDes = it.longDescription
                    val oldPrice = it.oldPrice?:"0.0"
                    val id = it.id
                    rating = it.rating

                    val data = featuredProductsItem2(name,price, image = image,rating,shortDes,longDes,oldPrice, id = id)
                    featuredList.add(data)
                }

                adapter.notifyDataSetChanged()
                stopShimmer(binding.shimmerLayoutFeatured,binding.featuredRecycle)

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
                if(InternetStatus.isOnline(requireContext())){
                    val currentItem = featuredList[position]
                    val productId = currentItem.id
                    cartPageViewModel.getCartResponse(productId,requireContext(),"1")
                    cartPageViewModel.result.observe(viewLifecycleOwner){
                        it.onSuccess {response->
                            totallCartProducts = response.Data.TotalShoppingCartProducts.toString()
                            Toast.makeText(requireContext(),"${response.Message}",Toast.LENGTH_LONG).show()
                            setShoppingCartQuantity(totallCartProducts)
                        }.onFailure {response->
                            Toast.makeText(requireContext(),"${response.cause?.cause}",Toast.LENGTH_LONG).show()
                            println(response.cause?.message)
                        }
                    }
                    println(productId)
                    println(currentItem.tittle)
                }else{
                    Toast.makeText(requireContext(),"No Internet Connection. Please Connect to a network.",Toast.LENGTH_LONG).show()
                }


            }

        })
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

        categoryListViewModel.getCategoryList(requireContext())
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
        val bannerDao = AppDatabase.getDatabase(requireContext()).bannerDao()
        //val sliderRepo = SliderRespository(bannerDao,requireContext())
        sliderViewModel.getSlider(requireContext(),bannerDao)
        sliderViewModel.sliderResult.observe(viewLifecycleOwner){
            it.onSuccess {response ->
                response.map {slider->
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

    private fun setShoppingCart(){
        shoppingCartViewModel.getCartProducts(requireContext())
        shoppingCartViewModel.response.observe(viewLifecycleOwner){
            it.onSuccess {
                val items = it.Data.Cart.Items.size.toString()
                println("Items :"+items)
                setShoppingCartQuantity(items)
            }.onFailure {

            }
        }
    }

    private fun setShoppingCartQuantity(quantity: String){
        binding.homePageCartBtn.text = quantity
    }




}