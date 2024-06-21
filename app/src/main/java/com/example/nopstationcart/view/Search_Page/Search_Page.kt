package com.example.nopstationcart.view.Search_Page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.compose.AsyncImage
import com.example.nopstationcart.Services.Model.Product_Search.Product
import com.example.nopstationcart.utils.NetworkResult

class Search_Page<PaddingValues> : Fragment() {
    private val viewModel by activityViewModels<ProductSearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MainAppScreen()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainAppScreen() {
        var textState by remember { mutableStateOf(TextFieldValue("")) }
        var productSize by remember { mutableStateOf("") }
        
        val products by viewModel.responseLiveData.observeAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TextField(
                value = textState,
                onValueChange = { textState = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(25.dp)),
                placeholder = {
                    Text(text = "Search...")
                },
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = { viewModel.searchProducts(textState.text) }) {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Green)
                    }
                }
            )
            Text(text = productSize, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp), color = Color.Green
            )

            when (products) {
                is NetworkResult.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is NetworkResult.Success -> {
                    val productsList = (products as NetworkResult.Success).data?.Data?.CatalogProductsModel?.Products

                    if (productsList != null) {
                        productSize = "${productsList.size} Results Found"
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            items(productsList) { product ->
                                    SingleItem(product)
                            }
                        }
                    } else {
                        productSize = "0 Results Found"
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "No Products Found")
                        }
                    }
                }
                is NetworkResult.Error ->{
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Error Loading Products")
                    }
                }
                else -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Search For Products")
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun PreviewMainApp() {
        MainAppScreen()
    }

    @Preview
    @Composable
    fun previewSingleItem() {

    }




    @Composable
    fun SingleItem(product: Product) {

        Card(onClick = {handleNavigation(product)},modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 20.dp, end = 15.dp, bottom = 10.dp),shape = RoundedCornerShape(15.dp), elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp)
                ) {
                    Column(modifier = Modifier) {
                        Text(
                            text = product.Name,
                            style = TextStyle(
                                fontSize = 18.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .padding(bottom = 10.dp, top = 10.dp)
                                .width(150.dp)
                        )
                        Text(
                            text = product.ProductPrice.Price,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .width(150.dp)
                                .padding(bottom = 10.dp)
                        )
                    }
                    AsyncImage(
                        model = product.PictureModels[0].ImageUrl,
                        contentDescription = "Product Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(120.dp)
                            .width(150.dp),
                    )
                }
            }
        }

    }

    private fun handleNavigation(product: Product) {
        println("Search product is clicked")
        val name = product.Name ?: "N/A"
        val imageUrl = product.PictureModels.getOrNull(0)?.ImageUrl ?: ""
        val price = product.ProductPrice.Price ?: "N/A"
        val shortDescription = product.ShortDescription ?: "N/A"
        val fullDescription = product.FullDescription ?: "N/A"
        val oldPrice = product.ProductPrice.OldPrice?.toString() ?: "N/A"
        val id = product.Id.toString() ?: "N/A"
        val action = Search_PageDirections.actionSearchPageToProductDeatils(name,imageUrl,price,shortDescription,fullDescription, oldPrice, id)
        findNavController().navigate(action)
    }
}
