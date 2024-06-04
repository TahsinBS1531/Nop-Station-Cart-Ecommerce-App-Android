package com.example.nopstationcart.view.Checkout

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.fragment.findNavController
import com.example.nopstationcart.R
import com.example.nopstationcart.view.components.CustomButton
import com.example.nopstationcart.view.components.CustomCheckBox
import com.example.nopstationcart.view.components.billingAddress
import com.example.nopstationcart.view.components.customText
import com.example.nopstationcart.view.components.customTextField
import com.example.nopstationcart.view.components.finalAmountBox

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
                                Text("2")
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
                    customTextField(label = "Existing Address:", value = existingAddress, isTrailingIcon =true )

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
                    customTextField(label = "New", value = billingAddress, isTrailingIcon =true )
                    customTextField(label = "First Name:", value = firstName, isTrailingIcon =false )
                    customTextField(label = "Last Name:", value = lastName, isTrailingIcon =false )
                    customTextField(label = "Email", value = email, isTrailingIcon =false )
                    customTextField(label = "Company", value = company, isTrailingIcon =false )
                    customTextField(label = "Country", value = country, isTrailingIcon =false )
                    customTextField(label = "State/Province:", value = state, isTrailingIcon =false )
                    customTextField(label = "Zip/Postal Code:", value = zip, isTrailingIcon =false )
                    customTextField(label = "City:", value = city, isTrailingIcon =false )
                    customTextField(label = "Phone Number:", value = phoneNumber, isTrailingIcon =false )
                    customTextField(label = "Fax Number:", value = faxNumber, isTrailingIcon =false )

                    billingAddress(value = "Payment Method")

                    CustomButton()
                    Spacer(modifier = Modifier.height(16.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(thickness = 2.dp, color = colorResource(id = R.color.hint_color))
                    Spacer(modifier = Modifier.height(16.dp))
                    billingAddress(value = "Payment Information")
                    finalAmountBox()


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