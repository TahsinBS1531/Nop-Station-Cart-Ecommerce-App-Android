package com.example.nopstationcart.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nopstationcart.R
import com.example.nopstationcart.view.Home_Page.Home_Page

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        //For Home page Fragment Setup
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, Home_Page())
            .commit()
    }
}