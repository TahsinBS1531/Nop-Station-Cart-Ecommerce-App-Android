package com.example.nopstationcart.view.Checkout

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.nopstationcart.R
import com.example.nopstationcart.Services.Local.OrderDetailsEntity
import com.example.nopstationcart.Services.Model.ShoppingCart.productCartItems
import com.example.nopstationcart.view.Checkout.Components.CustomButton
import com.example.nopstationcart.view.Checkout.Components.CustomCheckBox
import com.example.nopstationcart.view.Checkout.Components.billingAddress
import com.example.nopstationcart.view.Checkout.Components.customText
import com.example.nopstationcart.view.Checkout.Components.customTextField
import com.example.nopstationcart.view.Checkout.Components.finalAmountBox
import com.example.nopstationcart.view.Order_Details.OrderDetailsViewModel
import com.example.nopstationcart.view.Product_Shopping_Cart.RemoveCartViewModel
import com.example.nopstationcart.view.Product_Shopping_Cart.ShoppingCartViewModel
import java.util.Date

class Checkout_main<PaddingValues> : Fragment(R.layout.fragment_checkout_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                CheckoutScreen()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CheckoutScreen() {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        var isBtnClicked = remember { mutableStateOf(false) }

        var cartAmount by remember { mutableStateOf(0) }

        var text by remember { mutableStateOf("") }
        var existingAddress by remember { mutableStateOf("") }
        var billingAddress by remember { mutableStateOf("") }
        val checkedState = remember { mutableStateOf(true) }
        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var company by remember { mutableStateOf("") }
        var country by remember { mutableStateOf("") }
        var state by remember { mutableStateOf("") }
        var zip by remember { mutableStateOf("") }
        var city by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("") }
        var faxNumber by remember { mutableStateOf("") }

        //error checking
        var existingAddressError by remember { mutableStateOf("") }
        var billingAddressError by remember { mutableStateOf("") }
        var firstNameError by remember { mutableStateOf("") }
        var checkedStateError by remember { mutableStateOf("") }
        var lastNameError by remember { mutableStateOf("") }
        var emailError by remember { mutableStateOf("") }
        var companyError by remember { mutableStateOf("") }
        var countryError by remember { mutableStateOf("") }
        var stateError by remember { mutableStateOf("") }
        var zipError by remember { mutableStateOf("") }
        var cityError by remember { mutableStateOf("") }
        var phoneNumberError by remember { mutableStateOf("") }
        var faxNumberError by remember { mutableStateOf("") }

        fun validateFields(): Boolean {
            var isValid = true

            if (billingAddress.isBlank()) {
                billingAddressError = "Billing address is required"
                isValid = false
            } else {
                billingAddressError = ""
            }

            if (firstName.isBlank()) {
                firstNameError = "First name is required"
                isValid = false
            } else {
                firstNameError = ""
            }
            if (!checkedState.value) {
                checkedStateError = "CheckedState  is required"
                isValid = false
            } else {
                checkedStateError = ""
            }
            if (lastName.isBlank()) {
                lastNameError = "Last Name  is required"
                isValid = false
            } else {
                lastNameError = ""
            }
            if (email.isBlank()) {
                emailError = "Email  is required"
                isValid = false
            } else if(!email.contains('@')){
                emailError = "Not a Valid Email Format"
                isValid = false
            }
            else {
                emailError = ""
            }

            if (company.isBlank()) {
                companyError = "Company  is required"
                isValid = false
            } else {
                companyError = ""
            }
            if (country.isBlank()) {
                countryError = "Country  is required"
                isValid = false
            } else {
                countryError = ""
            }
            if (state.isBlank()) {
                stateError = "State  is required"
                isValid = false
            } else {
                stateError = ""
            }
            if (city.isBlank()) {
                cityError = "City  is required"
                isValid = false
            } else {
                cityError = ""
            }
            if (phoneNumber.isBlank()) {
                phoneNumberError = "Phone Number  is required"
                isValid = false
            } else {
                phoneNumberError = ""
            }


            return isValid
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier.background(brush = gradientColor()),
                    colors = topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("One Page Checkout",
                            color = Color.White,
                            fontSize = 18.sp,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Normal
                            )
                        )

                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            findNavController().popBackStack()
                        }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_arrow_back_24),
                                contentDescription = "nav Icon",
                                tint = Color.White
                            )
                        }
                    },
                    actions = {
                        BadgedBox(modifier = Modifier.padding(horizontal = 16.dp),badge = {
                            Badge(containerColor = Color.White){
                                Text(text = cartAmount.toString())
                            }
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.checkout),
                                contentDescription = "Cart Icon",
                                tint = Color.White,
                                modifier = Modifier.size(26.dp)
                            )

                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())) {
                OutlinedCard(modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                    shape = RoundedCornerShape(1.dp),
                    elevation = CardDefaults.cardElevation(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)) {
                    billingAddress(value = "Billing Address")
                    customText(value = "Address", color = colorResource(id = R.color.blue))
                    customTextField(label = "Existing Address:", value = existingAddress,onValueChange = { existingAddress = it },existingAddressError, isTrailingIcon =true,isBtnClicked )

                    Row(Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp)) {
                        CustomCheckBox(
                            checked = checkedState.value,
                            onCheckedChange = { checkedState.value = it }
                        )
                        Text(text = "Ship to the same address",
                            Modifier.padding(start = 16.dp, top = 2.dp),
                            fontSize = 14.sp
                        )
                    }

                    customText(value = "Select A Billing Address", color = colorResource(id = R.color.blue))
                    customTextField(label = "New", value = billingAddress,onValueChange = { billingAddress = it },billingAddressError, isTrailingIcon =true ,isBtnClicked)
                    customTextField(label = "First Name:", value = firstName, onValueChange = {firstName = it},firstNameError, isTrailingIcon =false,isBtnClicked )
                    customTextField(label = "Last Name:", value = lastName,onValueChange = { lastName = it },lastNameError, isTrailingIcon =false ,isBtnClicked)
                    customTextField(label = "Email", value = email,onValueChange = { email = it },emailError, isTrailingIcon =false,isBtnClicked )
                    customTextField(label = "Company", value = company,onValueChange = { company = it },companyError, isTrailingIcon =false ,isBtnClicked)
                    customTextField(label = "Country", value = country,onValueChange = { country = it },countryError, isTrailingIcon =false,isBtnClicked )
                    customTextField(label = "State/Province:", value = state,onValueChange = { state = it },stateError, isTrailingIcon =false,isBtnClicked )
                    customTextField(label = "Zip/Postal Code:", value = zip,onValueChange = { zip = it },zipError, isTrailingIcon =false,isBtnClicked )
                    customTextField(label = "City:", value = city,onValueChange = { city = it },cityError, isTrailingIcon =false ,isBtnClicked)
                    customTextField(label = "Phone Number:", value = phoneNumber,onValueChange = { phoneNumber = it },phoneNumberError, isTrailingIcon =false ,isBtnClicked)
                    customTextField(label = "Fax Number:", value = faxNumber,onValueChange = { faxNumber = it },faxNumberError, isTrailingIcon =false,isBtnClicked )

                    billingAddress(value = "Payment Method")

                    CustomButton()
                    Spacer(modifier = Modifier.height(16.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(thickness = 2.dp, color = colorResource(id = R.color.hint_color))
                    Spacer(modifier = Modifier.height(16.dp))
                    billingAddress(value = "Payment Information")
                    val viewModel: CheckoutViewModel by viewModels()
                    val shoppingCartViewModel: ShoppingCartViewModel by viewModels()
                    val orderDeatilsViewModel: OrderDetailsViewModel by viewModels()
                    val removeCartViewModel: RemoveCartViewModel by viewModels()
                    val action = Checkout_mainDirections.actionCheckoutMainToHomePage()

                    val sharedpreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
                    val token = sharedpreferences.getString("TOKEN", null)
                    var userEmail = sharedpreferences.getString("Email",null)

                    if(userEmail==null){
                        userEmail ="None"
                    }
                    //println("Email from the checkout page : $userEmail")

                    val navController = findNavController()
                    val validation = validateFields()
                    shoppingCartViewModel.getCartProducts(requireContext())
                    val list = ArrayList<productCartItems>()
                    shoppingCartViewModel.response.observe(viewLifecycleOwner){
                        it.onSuccess {
                            cartAmount = it.Data.Cart.Items.size
                            it.Data.Cart.Items.forEach {
                                //println("Product Name: ${it.ProductName}")
                                val product = productCartItems(it.ProductName,it.UnitPrice,it.Picture.ImageUrl,it.Quantity,it.ProductId)
                                list.add(product)
                            }
                        }
                    }

                    //println("Products size :"+list.size)
                    val currentDate = Date().toString()
                    val productsEntity = token?.let {
                        OrderDetailsEntity("", "Complete",currentDate,"",it,userEmail,existingAddress,billingAddress,firstName,lastName,email,company,country,state,zip,city,phoneNumber, faxNumber,"", products = list)
                    }

                    if (productsEntity != null) {
                        finalAmountBox(viewModel,navController,action,shoppingCartViewModel,validation,productsEntity,orderDeatilsViewModel,removeCartViewModel,isBtnClicked)
                    }


                }

            }
        }

    }

    private @Composable
    fun ScrollContent(innerPadding: androidx.compose.foundation.layout.PaddingValues) {

    }

    @Preview
    @Composable
    fun PreviewOfCheckoutScreen(){
        CheckoutScreen()
    }

    @Composable
    fun gradientColor() : Brush {
        return Brush.horizontalGradient(
            colors = listOf(
                Color(0xFF0BF7EB),
                Color(0xFF07C5FB),
                Color(0xFF088DF9)
            )
        )
    }

}