package com.example.nopstationcart.view.Order_Details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.nopstationcart.R
import com.example.nopstationcart.databinding.FragmentOrderDetailsInfoBinding
import com.example.nopstationcart.view.Single_Category_Page.Home_page_CategoryArgs
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class OrderDetailsInfo : Fragment() {


    private lateinit var binding: FragmentOrderDetailsInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOrderDetailsInfoBinding.inflate(inflater,container,false)
        displayOrderDetails()
        return binding.root

    }

    private fun displayOrderDetails() {
        val bundle = arguments
        val args = OrderDetailsInfoArgs.fromBundle(requireArguments())
        val name = args.Name
        val orderId = args.OrderId
        val email = args.Email
        val totalProducts = args.TotalProducts
        val orderStatus = args.OrderStatus
        val orderDate = args.OrderDate
        val orderTotal = args.OrderTotal
        val phone = args.Phone
        val fax = args.Fax
        val billingAddress = args.BillingAddress
        val country = args.Country
        val city = args.City
        val existingAddress = args.ExistingAddress


        binding.OrderDId.text = "Order Id: ${orderId}"
        binding.OrderDBillining.text = "Billing Address: ${billingAddress}"
        binding.OrderDCountry.text = "Country : ${country}"
        binding.OrderDDate.text = "Date: ${orderDate}"
        binding.OrderDEmail.text = "Email : ${email}"
        binding.OrderDFax.text = "Fax : ${fax}"
        binding.OrderDPhone.text = "Phone : ${phone}"
        binding.OrderDShippingAddress.text = "Existing Address: ${existingAddress}"
        binding.OrderDStatus.text = "Order Status: ${orderStatus}"
        binding.OrderDTotal.text = "Order Total: ${orderTotal}"
        binding.OrderDemail2.text = "Email : ${email}"
        binding.OrderDetUserName.text = "Name : ${name}"


        binding.OrderDBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }



    }

}