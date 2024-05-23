package com.example.nopstationcart.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.nopstationcart.R
import com.example.nopstationcart.view.Home_Page.Home_Page
import com.example.nopstationcart.view.Home_Page.Home_PageDirections
import com.example.nopstationcart.view.Login_Page.login_main
import com.example.nopstationcart.view.Login_Page.login_mainDirections
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNav:BottomNavigationView
    lateinit var navcontroller:NavController
    lateinit var sharedPreferences:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNav = findViewById(R.id.bottom_navigation)
        bottomNav.setupWithNavController(navController)

        sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("TOKEN", null)
        //Home_Page().handleLogOut(token)

//        if(token!=null){
//            Toast.makeText(this,"User already Logged In",Toast.LENGTH_LONG).show()
//            navController.navigate(R.id.home_Page)
//        }else{
//            navController.navigate(R.id.login_main)
//        }


        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.home_Page -> bottomNav.visibility = View.VISIBLE
                R.id.login_main -> bottomNav.visibility = View.GONE
                R.id.home_page_Category ->bottomNav.visibility = View.GONE
                else -> bottomNav.visibility = View.VISIBLE
            }
        }


    }


}