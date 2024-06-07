package com.example.nopstationcart.view.components

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.nopstationcart.viewmodel.CheckoutViewModel
import com.example.nopstationcart.viewmodel.ShoppingCartViewModel


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
fun customTextField(label :String, value:String,isTrailingIcon:Boolean) {
    var existingAddress by remember { mutableStateOf("") }

    TextField(
        value = value,
        onValueChange = {existingAddress = it},
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        label = { Text(label) },
        trailingIcon = {
            if (isTrailingIcon) {
                // Add your desired trailing icon here (e.g., IconButton, ImageVector)
                IconButton(onClick = { /* Handle icon click here */ }) {
                    Icon( // Replace with your desired icon content (e.g., Icons.Clear)
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


@Composable
fun finalAmountBox(viewModel: CheckoutViewModel, navController: NavController, action: NavDirections, shoppingCartViewModel: ShoppingCartViewModel) {

    val context = LocalContext.current

    val response by viewModel.result.observeAsState()
    val shoppingCartResult by shoppingCartViewModel.response.observeAsState()
    var subtotal =""
    var Shipping =""
    var Discount =""
    var orderTotal =""

    // Handle the response inside a LaunchedEffect
    LaunchedEffect(response) {
        response?.let {
            it.onSuccess {
                Toast.makeText(context, "${it.message}, ${it.orderId}", Toast.LENGTH_LONG).show()
                println(it.message)
                navController.navigate(action)
            }.onFailure {
                println(it)
                println("Failed API checkout")
                Toast.makeText(context, "Checkout API Failed", Toast.LENGTH_LONG).show()
            }
        }
    }

    OutlinedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .height(200.dp),
        colors = CardDefaults.outlinedCardColors(containerColor = colorResource(id = R.color.white)),
        shape = RoundedCornerShape(4.dp)
    ) {
        
        Spacer(modifier = Modifier.height(10.dp))
        shoppingCartViewModel.getCartProducts(context)
        shoppingCartResult?.let {
            it.onSuccess {
                subtotal = it.Data.OrderTotals.SubTotal.toString()
                Shipping = it.Data.OrderTotals.Shipping.toString()
                Discount = it.Data.OrderTotals.OrderTotalDiscount
                orderTotal = it.Data.OrderTotals.OrderTotal.toString()
            }.onFailure {
                subtotal = "0"
                Shipping = "0"
                Discount = "0"
                orderTotal = "0"
            }
        }
        TextField("Sub-Total:",subtotal)
        TextField("Shipping:",Shipping)
        TextField("Discount Amount:",Discount)
        TextField("Totall:",orderTotal)
        Spacer(modifier = Modifier.height(10.dp))
        Spacer(modifier = Modifier.height(10.dp))
        TextButton(onClick = { viewModel.getResponse() },
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
                )) {
            Text(text = "Confirm", color = colorResource(id = R.color.white))
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