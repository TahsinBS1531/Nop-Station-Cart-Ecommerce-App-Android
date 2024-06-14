package com.example.nopstationcart.view.Checkout

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nopstationcart.R

class PracticeUI : Fragment(R.layout.fragment_practice_u_i) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        }
    }

    @Preview
    @Composable
    fun AppPreview(){
        MainAppScreen()
    }

    @Composable
    fun FirstSection(name:String = " Tahsin "){
        Row(modifier = Modifier.fillMaxWidth().padding(top = 50.dp, start = 20.dp, end = 10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text(text = "Good Morning, $name", style = TextStyle(fontSize = 20.sp, color = Color.White, fontFamily = FontFamily.Serif), modifier = Modifier.padding(bottom = 5.dp))
                Text(text = "We wish you have a good day!", style = TextStyle(color = Color.White))
            }
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.height(40.dp)) {
                Icon(Icons.Outlined.Search, contentDescription ="Search" , tint = Color.White, modifier = Modifier.size(30.dp))
            }
        }
    }

}