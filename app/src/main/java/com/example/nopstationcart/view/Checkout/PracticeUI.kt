package com.example.nopstationcart.view.Checkout

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nopstationcart.R

class PracticeUI : Fragment(R.layout.fragment_practice_u_i) {

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
                MainAppScreen()
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    @Composable
    fun MainAppScreen(){
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.MainBackground))
        ,
            horizontalAlignment = Alignment.CenterHorizontally) {
            FirstSection("Tahsin Ahmed")
            val featureItems = ArrayList<FourthSectionDataClass>()
            val items1 = FourthSectionDataClass("Good Meditation",Icons.Filled.Favorite,R.drawable.feature1)
            val items2 = FourthSectionDataClass("Tips For Sleeping",Icons.Filled.CheckCircle,R.drawable.feature2)
            val items3 = FourthSectionDataClass("Night Island",Icons.Filled.Favorite,R.drawable.feature3)
            val items4 = FourthSectionDataClass("Calming Sounds",Icons.Filled.Face,R.drawable.feature4)
            featureItems.add(items1)
            featureItems.add(items2)
            featureItems.add(items3)
            featureItems.add(items4)
            val items = listOf("Sweet Sleep","Meditation","Good Health","Morning Walk","Evening Meal")
            SecondSection(items)
            ThirdSection()
            Text(text = "Featured", modifier = Modifier
                .padding(top = 25.dp, start = 20.dp, bottom = 25.dp)
                .fillMaxWidth(), style = TextStyle(color = Color.White, textAlign = TextAlign.Start, fontSize = 20.sp, fontWeight = FontWeight.Bold))

            FourthSection(feature = featureItems)
        }
    }

    @Preview
    @Composable
    fun AppPreview(){
        MainAppScreen()
    }

    @Composable
    fun FirstSection(name:String = " Tahsin "){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp, start = 20.dp, end = 10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text(text = "Good Morning, $name", style = TextStyle(color = Color.White, fontSize = 18.sp),
                    modifier = Modifier.padding(bottom = 10.dp))
                Text(text = "We wish you have a good day!", style = TextStyle(color = Color.White, fontSize = 14.sp))
            }
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.height(40.dp)) {
                Icon(Icons.Outlined.Search, contentDescription ="Search" , tint = Color.White, modifier = Modifier.size(35.dp))
            }
        }
    }

    @Composable
    fun SecondSection(chips:List<String>){
        var selectedChipIndex by remember {
            mutableStateOf(0)
        }
        LazyRow(modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp), verticalAlignment = Alignment.CenterVertically){
            items(chips.size){
                Box(contentAlignment = Alignment.Center,modifier = Modifier
                    .padding(end = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .clickable {
                        selectedChipIndex = it
                    }
                    .background(
                        if (selectedChipIndex == it) colorResource(id = R.color.BoxActive)
                        else colorResource(id = R.color.BoxColor)
                    )
                    .padding(15.dp)
                ){
                    Text(text = chips[it], style = TextStyle(color = Color.White))
                }
            }

        }

    }

    @Composable
    fun ThirdSection(){
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(color = colorResource(id = R.color.thirdColBackgroundColor))) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically,modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 25.dp)) {
                Column {

                    Text(text = "Daily Thought", style = TextStyle(fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold), modifier = Modifier.padding(bottom = 10.dp))
                    Text(text = "Meditaion : 3-10 Minutes", style = TextStyle(color = Color.White, fontSize = 14.sp,fontWeight = FontWeight.Bold))
                }
                Box(contentAlignment = Alignment.Center,modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color = colorResource(id = R.color.BoxActive))
                    .padding(10.dp)) {
                    Icon(Icons.Filled.PlayArrow, contentDescription = "Play", tint = Color.White )

                }
            }
        }
    }

    @Composable
    fun FourthSection(feature:List<FourthSectionDataClass>){
        Column(modifier = Modifier.fillMaxWidth()) {
            LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(start = 7.5.dp, end = 12.5.dp, bottom = 100.dp)
            , modifier = Modifier.fillMaxWidth()){
                items(feature.size){
                    SingleItemFourth(feature = feature[it])
                }
            }

        }
    }

    @Composable
    fun SingleItemFourth(feature:FourthSectionDataClass){
        val backgroundPainter: Painter = painterResource(id = feature.backgroundImg)

        Box(
            modifier = Modifier
                .padding(7.5.dp)
                .clip(RoundedCornerShape(10.dp))
                .aspectRatio(0.8f)
        ) {
            Image(
                painter = backgroundPainter,
                contentDescription = null, // No content description as it's for decorative purposes
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
                Text(text = feature.title, style = TextStyle(fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold), modifier = Modifier.padding(start = 15.dp, top = 20.dp, end = 15.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, bottom = 20.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                    IconButton(onClick = {  }) {
                        Icon(imageVector = feature.icon, contentDescription = "Null",tint = Color.White, modifier = Modifier.size(35.dp))
                    }
                    Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors()
                    ) {
                        Text(text = "Start")
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun SingleItemPreview(){
        val data = FourthSectionDataClass("Good Meditation",Icons.Filled.Favorite,R.drawable.feature1)
        SingleItemFourth(feature = data)
    }



}