package com.example.nopstationcart.view

import android.os.Bundle
import android.view.View
import android.window.OnBackInvokedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nopstationcart.R
import com.example.nopstationcart.view.Home_Page.Home_Page
import com.example.nopstationcart.view.Login_Page.login_main
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNav:BottomNavigationView
    val home_page_fragment = Home_Page()
    val login_page_fragment = login_main()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setCurrentFragment(login_page_fragment)
        bottomNav = findViewById(R.id.bottom_navigation)

        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.bottomAccount -> {
                    setCurrentFragment(login_page_fragment)
                    bottomNav.visibility = View.GONE
                    true
                }
                R.id.bottomHome -> {
                    setCurrentFragment(home_page_fragment)
                    bottomNav.visibility = View.VISIBLE
                    true
                    //bottomNav.selectedItemId =R.id.bottomHome
                }
                else -> true
            }
        }


    }

    private fun setCurrentFragment(fragment:Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer,fragment)
        //transaction.addToBackStack(null)
        transaction.commit()
        println(fragment)

    }

    fun setBottomNavigationVisibility(isVisible: Boolean) {
        bottomNav.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

}