package com.example.nopstationcart.view.Single_Category_Page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nopstationcart.R
import com.example.nopstationcart.Services.Interfaces.categoryDetailsOnClicklistener
import com.example.nopstationcart.Services.Model.CategoryList.Product
import com.example.nopstationcart.viewmodel.CartViewModel


class Home_page_Category : Fragment() {
    lateinit var backBtn: Toolbar
    private val cartPageViewModel: CartViewModel by viewModels()
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
            val items: List<Product> = args.productListItem.toList()
            val title = args.productTittle


            val textView = view.findViewById<TextView>(R.id.singleCategoryTittle)
            textView.text = title

            val imageView = view.findViewById<ImageView>(R.id.singleCategoryImg)

            Glide.with(this)
                .load(imageResId)
                .into(imageView)


            val recycle = view.findViewById<RecyclerView>(R.id.singleCategoryRecycle)
            recycle.layoutManager = GridLayoutManager(requireContext(),2)
            val adapter = CategoryDetailsAdapter(items)
            recycle.adapter = adapter

            adapter.setOnItemClick(object : categoryDetailsOnClicklistener {
                override fun onItemClick(position: Int) {
                    val currentItem = items[position]
                    val title = currentItem.Name ?: "No Title"
                    val imageResId = currentItem.PictureModels.getOrNull(0)?.FullSizeImageUrl ?: ""
                    val itemPrice = currentItem.ProductPrice.Price ?: "0.0"
                    val oldPrice = currentItem.ProductPrice.OldPrice ?: "0.0"
                    val shortDescription = currentItem.ShortDescription ?: "No short description available"
                    val longDes = currentItem.FullDescription ?: "No full description available"
                    val productId = currentItem.Id

                    val action = Home_page_CategoryDirections.actionHomePageCategoryToProductDeatils(productTittile = title, productImage = imageResId, productPrice = itemPrice,oldPrice= oldPrice, shortDescription = shortDescription, fullDescription = longDes, productId = productId.toString())
                    findNavController().navigate(action)
                }

                override fun onCartBtnClick(position: Int) {
                    val currentItem = items[position]
                    val productId = currentItem.Id
                    cartPageViewModel.getCartResponse(productId,requireContext(),"1")
                    cartPageViewModel.result.observe(viewLifecycleOwner){
                        it.onSuccess {response->
                            Toast.makeText(requireContext(),"${response.Message}", Toast.LENGTH_LONG).show()
                        }.onFailure {response->
                            Toast.makeText(requireContext(),"${response.cause?.cause}", Toast.LENGTH_LONG).show()
                            println(response.cause?.message)
                        }
                    }
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