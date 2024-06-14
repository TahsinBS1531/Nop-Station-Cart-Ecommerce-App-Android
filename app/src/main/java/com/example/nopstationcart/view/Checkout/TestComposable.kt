package com.example.nopstationcart.view.Checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nopstationcart.R


class TestComposable : Fragment(R.layout.fragment_test_composable) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireContext()).apply {
            setContent {
                TestScreen()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TestScreen(){
        val scrollState = rememberScrollState()
        val context = LocalContext.current
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = ("Scafold Example"))
            })
        },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    Toast.makeText(context,"Item Added",Toast.LENGTH_SHORT).show()
                }) {

                    Icon(Icons.Filled.Add, contentDescription ="Add" )
                }
            },
            floatingActionButtonPosition = FabPosition.End,

            bottomBar = {
                BottomAppBar(content = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Home, contentDescription ="Home" )

                    }
//                    Spacer(modifier = Modifier.weight(0.1f,true))
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.AccountCircle, contentDescription ="Account" )
                    }
                })
            },
            content = {padding->
                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Cyan)
                    .padding(padding)
                    .padding(16.dp)) {
                    items(50){index->
                        Text(text = "Content $index")
                        Spacer(modifier = Modifier.height(8.dp))

                    }
                }
            })
    }

    @Preview
    @Composable
    fun TestScreenPreview(){
        TestScreen()

    }

}
