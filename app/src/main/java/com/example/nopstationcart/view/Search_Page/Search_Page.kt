package com.example.nopstationcart.view.Search_Page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.compose.AsyncImage
import com.example.nopstationcart.Services.Model.Product_Search.AutocompleteSuggestionResponse
import com.example.nopstationcart.Services.Model.Product_Search.Product
import com.example.nopstationcart.utils.KhandFont
import com.example.nopstationcart.utils.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        val autocompleData by viewModel.autoSuggestionLiveData.observeAsState()

        var searchJob: Job?=null

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TextField(
                value = textState,
                onValueChange = {newText->
                    textState = newText
                    searchJob?.cancel()
                    searchJob = CoroutineScope(Dispatchers.Main).launch {
                        delay(500)
                        viewModel.getAutoSuggestion(newText.text)
                    }
                                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = {
                    Text(text = "Search...", style = TextStyle(fontFamily = KhandFont, fontSize = 16.sp))
                },
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = { viewModel.searchProducts(textState.text)
                    viewModel.clearSuggestion()}) {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Green)
                    }
                }
            )

            if(autocompleData is NetworkResult.Success){
                val suggestions = (autocompleData as NetworkResult.Success<AutocompleteSuggestionResponse>).data
                val suggestionList = suggestions?.Data?.map { it.label } ?: emptyList()

                LazyColumn(){
                    items(suggestionList.size){
                        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically,modifier = Modifier.fillMaxWidth().padding(start = 16.dp,end=16.dp)
                            .clickable {
                                val data = suggestionList[it]
                                textState = TextFieldValue(data)
                                viewModel.clearSuggestion()
                                viewModel.searchProducts(data)
                            }) {
                            Text(suggestionList[it], modifier = Modifier.weight(0.8f), style = TextStyle(fontFamily = KhandFont, fontSize = 16.sp))
                            IconButton(onClick = { textState = TextFieldValue(suggestionList[it])
                            viewModel.clearSuggestion()}) {
                                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Use suggestion", tint = Color.Green)
                            }

                        }

                    }
                }
            }

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
        val name = product.Name
        val imageUrl = product.PictureModels.getOrNull(0)?.ImageUrl ?: ""
        val price = product.ProductPrice.Price
        val shortDescription = product.ShortDescription
        val fullDescription = product.FullDescription
        val oldPrice = product.ProductPrice.OldPrice?.toString()?:"0"
        val id = product.Id.toString()
        val action = Search_PageDirections.actionSearchPageToProductDeatils(name,imageUrl,price,shortDescription,fullDescription, oldPrice, id)
        findNavController().navigate(action)
    }
}
