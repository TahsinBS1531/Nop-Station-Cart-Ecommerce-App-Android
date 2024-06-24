package com.example.nopstationcart.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.activity.viewModels
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
import com.example.nopstationcart.view.Search_Page.ProductSearchViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityMainBinding
    private val productSearchViewModel: ProductSearchViewModel by viewModels()

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
        }else{
            Toast.makeText(this, "Guest User", Toast.LENGTH_SHORT).show()
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.home_Page -> {
                    bottomNav.visibility = View.VISIBLE
                    clearSearchState()
                }
                R.id.product_Deatils ->{
                    bottomNav.visibility = View.GONE
                    clearSearchState()
                }
                R.id.search_Page->{
                    bottomNav.visibility = View.VISIBLE
                    clearSearchState()
                }
                R.id.logOutMain -> {
                    clearSearchState()
                    val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
                    val token = sharedPreferences.getString("TOKEN", null)
                    if (token != null) {
                        println("User already has token: $token")
                        Toast.makeText(this, "User already Logged In", Toast.LENGTH_LONG).show()
                    } else {
                        navController.navigate(R.id.login_main)
                        bottomNav.visibility = View.GONE
                    }
                }
                R.id.login_main->{
                    bottomNav.visibility = View.GONE
                    clearSearchState()
                }
                R.id.home_page_Category -> {
                    bottomNav.visibility = View.GONE
                    clearSearchState()
                }
                R.id.category_Fragment ->{
                    bottomNav.visibility = View.VISIBLE
                    clearSearchState()
                }
                R.id.order_Details-> clearSearchState()

                else -> bottomNav.visibility = View.VISIBLE
            }
        }
    }

    private fun clearSearchState(){
        productSearchViewModel.clearSuggestion()
        productSearchViewModel.clearSearchResult()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val navController = findNavController(R.id.fragmentContainer)
        if (!navController.popBackStack()) {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainer)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}