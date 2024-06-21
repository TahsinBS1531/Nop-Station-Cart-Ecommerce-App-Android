package com.example.nopstationcart.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.nopstationcart.R
import com.example.nopstationcart.databinding.ActivityMainBinding
import com.example.nopstationcart.view.Home_Page.Home_Page
import com.example.nopstationcart.view.Home_Page.Home_PageDirections
import com.example.nopstationcart.view.Login_Page.login_main
import com.example.nopstationcart.view.Login_Page.login_mainDirections
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController
        bottomNav = findViewById(R.id.bottom_navigation)
        bottomNav.setupWithNavController(navController)

        sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("TOKEN", null)
        println("Token : $token")

        if (token != null) {
            Toast.makeText(this, "User already Logged In", Toast.LENGTH_LONG).show()
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.home_Page -> {
                    bottomNav.visibility = View.VISIBLE
                }
                R.id.product_Deatils ->{
                    bottomNav.visibility = View.GONE
                }
                R.id.logOutMain -> {
                    val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
                    val token = sharedPreferences.getString("TOKEN", null)
                    if (token != null) {
                        //navController.navigate(R.id.home_Page)
                        //navController.navigate(R.id.logOutMain)
                        println("User already has token: $token")
                        Toast.makeText(this, "User already Logged In", Toast.LENGTH_LONG).show()
                    } else {
                        navController.navigate(R.id.login_main)
                        bottomNav.visibility = View.GONE
                    }
                }
                R.id.login_main->{
                    bottomNav.visibility = View.GONE
                }
                R.id.home_page_Category -> bottomNav.visibility = View.GONE
                else -> bottomNav.visibility = View.VISIBLE
            }
        }
    }
}