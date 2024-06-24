package com.example.nopstationcart.view.Checkout.Components

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.example.nopstationcart.R
import com.example.nopstationcart.Services.Local.OrderDetailsEntity
import com.example.nopstationcart.Services.Model.Checkout.CheckoutResponse
import com.example.nopstationcart.Services.Model.Remove_Cart.FormValue
import com.example.nopstationcart.Services.Model.Remove_Cart.RemoveCartRequest
import com.example.nopstationcart.Services.Model.ShoppingCart.CartProductsResponse
import com.example.nopstationcart.Services.Model.ShoppingCart.productCartItems
import com.example.nopstationcart.view.Checkout.CheckoutViewModel
import com.example.nopstationcart.view.Order_Details.OrderDetailsViewModel
import com.example.nopstationcart.view.Product_Shopping_Cart.RemoveCartViewModel
import com.example.nopstationcart.view.Product_Shopping_Cart.ShoppingCartViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Date



@Composable
fun billingAddress(value:String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(33.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF0BF7EB),
                        Color(0xFF07C5FB),
                        Color(0xFF088DF9)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset.Infinite
                )
            )

    ) {
        Text(
            text = value,
            modifier = Modifier
                .fillMaxWidth()
                .height(33.dp)
                .padding(start = 20.dp, top = 6.dp),
            style = TextStyle(color = Color.White)
        )
    }
}

@Composable
fun customText(value:String, color: Color){
    Text(text = value,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 16.dp),
        style = TextStyle(fontWeight = FontWeight.Bold, color = color)
    )
}

@Composable
fun customTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    error: String,
    isTrailingIcon: Boolean,
    isBtnClicked: MutableState<Boolean>
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        label = { Text(label) },
        trailingIcon = {
            if (isTrailingIcon) {
                IconButton(onClick = { /* Handle icon click here */ }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Clear text"
                    )
                }
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = colorResource(id = R.color.white),
            unfocusedContainerColor = colorResource(id = R.color.white),
            focusedIndicatorColor = colorResource(id = R.color.hint_color),
            unfocusedIndicatorColor = colorResource(id = R.color.hint_color),
            unfocusedLabelColor = colorResource(id = R.color.hint_color),
            focusedLabelColor = colorResource(id = R.color.blue)
        ),
    )
}

@Composable
fun CustomCheckBox(checked: Boolean,
                   onCheckedChange: (Boolean) -> Unit = {}){
    Box(
        modifier = Modifier
            .size(width = 20.dp, height = 20.dp)
            .clip(shape = RoundedCornerShape(2.dp))
            .border(1.dp, colorResource(id = R.color.hint_color), shape = RoundedCornerShape(2.dp))
            .padding(start = 4.dp, top = 3.dp)
            .clickable { onCheckedChange(!checked) }
    ) {
        if (checked) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.correct_icon),
                contentDescription = "checked"
            )
        }
    }

}

@Composable
fun CustomButton(){

    val isCheckedCheck = remember { mutableStateOf(true) }
    val isCheckedCard = remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .height(145.dp)
        .padding(start = 16.dp, end = 16.dp)
    ) {
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp))

        Box(modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, colorResource(id = R.color.hint_color))){
            Row(modifier = Modifier
                .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically) {
                ButtonCheck(isChecked = isCheckedCheck.value,
                    onCheckedChange = {isCheckedCheck.value = true
                    isCheckedCard.value =false
                    }
                )
                Image(painter = painterResource(id = R.drawable.check), contentDescription = "Cash",Modifier.padding(start = 10.dp))
                Text(text = "Check/ Money Order", modifier = Modifier.padding(start = 8.dp))

            }
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp)
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, colorResource(id = R.color.hint_color))
        ) {
            Row(modifier = Modifier
                .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically) {
                ButtonCheck(isChecked = isCheckedCard.value, onCheckedChange = {isCheckedCheck.value =false
                isCheckedCard.value =true
                }
                )
                androidx.compose.foundation.Image(painter = painterResource(id = R.drawable.credit_card), contentDescription = "cash",Modifier.padding(start = 10.dp))
                Text(text = "Credit Card", modifier = Modifier.padding(start = 8.dp))
            }

        }


    }

    
}


@Composable
fun ButtonCheck(
    isChecked: Boolean = true,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .size(width = 25.dp, height = 25.dp)
            .clip(shape = RoundedCornerShape(12.5.dp))
            .border(
                1.5.dp,
                colorResource(id = R.color.hint_color),
                shape = RoundedCornerShape(12.5.dp)
            )
            .clickable { onCheckedChange(!isChecked) },
        contentAlignment = Alignment.Center
    ) {
        if (isChecked) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.correct_icon),
                contentDescription = "checked"
            )
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun finalAmountBox(
    viewModel: CheckoutViewModel,
    navController: NavController,
    action: NavDirections,
    shoppingCartViewModel: ShoppingCartViewModel,
    validation: Boolean,
    productsEntity: OrderDetailsEntity?,
    orderDetailsViewModel: OrderDetailsViewModel,
    removeCartViewModel: RemoveCartViewModel,
    isBtnClicked: MutableState<Boolean>
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val response by viewModel.result.observeAsState()
    val removeCart by removeCartViewModel.response.observeAsState()
    val shoppingCartResult by shoppingCartViewModel.response.observeAsState()

    var subtotal by remember { mutableStateOf("0") }
    var shipping by remember { mutableStateOf("0") }
    var discount by remember { mutableStateOf("0") }
    var orderTotal by remember { mutableStateOf("0") }
    var isLoading by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        shoppingCartViewModel.getCartProducts(context)
        viewModel.getResponse()
    }

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(200.dp),
        colors = CardDefaults.outlinedCardColors(containerColor = colorResource(id = R.color.white)),
        shape = RoundedCornerShape(4.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        shoppingCartResult?.let {
            it.onSuccess { data ->
                subtotal = data.Data.OrderTotals.SubTotal.toString()
                shipping = data.Data.OrderTotals.Shipping.toString()
                discount = data.Data.OrderTotals.OrderTotalDiscount ?: "0"
                orderTotal = data.Data.OrderTotals.OrderTotal.toString()
            }.onFailure {
                subtotal = "0"
                shipping = "0"
                discount = "0"
                orderTotal = "0"
            }
        }

        TextField("Sub-Total", subtotal)
        TextField("Shipping:", shipping)
        TextField("Discount Amount:", discount)
        TextField("Total:", orderTotal)

        Spacer(modifier = Modifier.height(10.dp))

        if(isLoading){
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
            )
        }else{
            TextButton(
                onClick = {
                    if(validation){
                        isBtnClicked.value=true
                        isLoading = true
                        println("Checkout button is clicked")
                        //viewModel.getResponse()
                        response?.let {
                            it.onSuccess { checkoutResponse ->
                                isLoading = false
                                println("API Response Successful")
                                Toast.makeText(context, "${checkoutResponse.message}, ${checkoutResponse.orderId}", Toast.LENGTH_LONG).show()
                                addOrderData(checkoutResponse, productsEntity, coroutineScope, shoppingCartResult, removeCartViewModel, context, orderDetailsViewModel, navController, action, orderTotal)
                            }.onFailure {
                                isLoading = false
                                println("Failed API checkout")
                                Toast.makeText(context, "Checkout API Failed", Toast.LENGTH_LONG).show()
                            }
                        }

                    }else{
                        Toast.makeText(context,"Please Fill all the fields",Toast.LENGTH_LONG).show()
                    }

                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 15.dp, end = 15.dp)
                    .fillMaxWidth()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF0BF7EB),
                                Color(0xFF07C5FB),
                                Color(0xFF088DF9)
                            ),
                            start = Offset(0f, 0f),
                            end = Offset.Infinite
                        )
                    )
            ) {
                Text(text = "Confirm", color = colorResource(id = R.color.white))
            }
        }

    }


    removeCart?.let {
        it.onSuccess {
            println("Removed API success")
        }.onFailure {
            println("Removed API unsuccess")
        }
    }
}

private fun removeCartItems(products: ArrayList<FormValue>, removeCartViewModel: RemoveCartViewModel, context: Context) {
    val request = RemoveCartRequest(products)
    removeCartViewModel.getTheCartRemoved(request, context)
}

private fun addOrderData(
    checkoutResponse: CheckoutResponse,
    productsEntity: OrderDetailsEntity?,
    coroutineScope: CoroutineScope,
    shoppingCartResult: Result<CartProductsResponse>?,
    removeCartViewModel: RemoveCartViewModel,
    context: Context,
    orderDetailsViewModel: OrderDetailsViewModel,
    navController: NavController,
    action: NavDirections,
    orderTotal: String
){
    coroutineScope.launch {
        if (productsEntity != null) {
            shoppingCartResult?.let {
                it.onSuccess { data ->
                    val list = ArrayList<productCartItems>()
                    val removeList = ArrayList<FormValue>()
                    list.clear()
                    removeList.clear()
                    data.Data.Cart.Items.forEach { item ->

                        val product = productCartItems(item.ProductName, item.UnitPrice, item.Picture.ImageUrl, item.Quantity, item.ProductId,item.SubTotalValue.toString())
                        list.add(product)
                        val productId = item.Id.toString()
                        val formValue = FormValue("removefromcart", productId)
                        removeList.add(formValue)
                    }
                    removeCartItems(removeList, removeCartViewModel, context)
                    val currentDate = Date()
                    productsEntity.products = list
                    productsEntity.orderId = checkoutResponse.orderId
                    productsEntity.total_amount = list.size.toString()
                    productsEntity.orderTotal = orderTotal
                    productsEntity.orderDate = currentDate.toString()
                    orderDetailsViewModel.saveOrderDetails(context, productsEntity)
                    navController.navigate(action)
                }.onFailure {
                    println("Failed to fetch cart products")
                }
            }
        }

    }
}




@Composable
fun TextField(title:String, amount:String, mode:String ="default"){
    lateinit var style: FontWeight
    var textSize = 2.sp
    var textColor = colorResource(id = R.color.black)

    when (mode) {
        "small&faded" -> {
            style = FontWeight.Normal
            textSize = 12.sp
            textColor = colorResource(id = R.color.hint_color)
        }

        "bold" -> {
            style = FontWeight.Bold
            textSize = 16.sp
        }

        "extraBold" -> {
            style = FontWeight.ExtraBold
            textSize = 18.sp
        }

        else -> {
            style = FontWeight.Normal
            textSize = 14.sp
        }
    }

    Row(modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 4.dp, end = 16.dp)) {
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(.5f),
            textAlign = TextAlign.Start,
            fontWeight = style,
            fontSize = textSize,
            color = textColor
        )
        Text(
            text = amount,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            textAlign = TextAlign.End,
            fontWeight = style,
            fontSize = textSize,
            color = textColor
        )
    }
}