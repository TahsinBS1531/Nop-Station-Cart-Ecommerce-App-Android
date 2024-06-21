package com.example.nopstationcart.view.Order_Details

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nopstationcart.R
import com.example.nopstationcart.Services.Interfaces.ItemClickListener
import com.example.nopstationcart.Services.Model.OrderDetailsItem
import com.example.nopstationcart.Services.Model.ShoppingCart.productCartItems
import com.example.nopstationcart.databinding.FragmentOrderDetailsBinding
import com.example.nopstationcart.view.Adapters.OrderDetailsAdapter
import kotlinx.coroutines.launch

class Order_Details : Fragment() {

    private lateinit var binding: FragmentOrderDetailsBinding
    private val orderDetailsViewModel: OrderDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_order__details, container, false)
        binding = FragmentOrderDetailsBinding.bind(view)

        val sharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val userEmail = sharedPreferences.getString("Email", null)
        println("Email from the Order Details Page : $userEmail")

        if (userEmail != null) {
            viewLifecycleOwner.lifecycleScope.launch {
                orderDetailsViewModel.getOrderDetails(userEmail, requireContext())
            }

            val orderList = ArrayList<OrderDetailsItem>()
            val recyclerView = binding.orderDetailsRecycle
            recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            val adapter = OrderDetailsAdapter(orderList)
            recyclerView.adapter = adapter

            orderDetailsViewModel.orderDetails.observe(viewLifecycleOwner) { orderDetails ->
                orderList.clear()
                orderDetails.forEach {
                    val name = it.firstName + " " + it.lastName
                    val orderId = it.orderId
                    val email = it.email
                    val totalProducts = it.products.size
                    val products = it.products
                    val listItem = OrderDetailsItem(name, orderId, email, totalProducts.toString(),it.orderStatus,it.orderDate,it.orderTotal,it.phone_number,it.fax_number,it.billing_address,it.country,it.city,it.existing_address,it.state, products)
                    orderList.add(listItem)
                }
                if(orderList.size ==0){
                    Toast.makeText(requireContext(),"Your Order List is Empty",Toast.LENGTH_LONG).show()
                }
                adapter.notifyDataSetChanged()
            }

            adapter.setOnItemClick(object : ItemClickListener {
                override fun onItemClick(position: Int) {
                    val currentItem = orderList[position]
                    Toast.makeText(requireContext(), "${orderList[position].orderId} is clicked", Toast.LENGTH_LONG).show()
                    val products = currentItem.products
                    val action = Order_DetailsDirections.actionOrderDetailsToOrderDetailsInfo(currentItem.name,currentItem.orderId,currentItem.email,currentItem.totalProducts,currentItem.orderStatus,currentItem.orderDate,currentItem.orderTotal,currentItem.phone,currentItem.fax,currentItem.billingAddress,currentItem.country,currentItem.city,currentItem.existingAddress,products.toTypedArray())
                    findNavController().navigate(action)
                }

                override fun onCartBtnClick(position: Int) {
                }
            })


        }else{
            Toast.makeText(requireContext(), "User Not Logged in to see Details",Toast.LENGTH_LONG).show()
        }


        return binding.root
    }
}
