package com.example.nopstationcart.view.Login_Page

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.nopstationcart.R
import com.example.nopstationcart.view.Home_Page.Home_Page
import com.example.nopstationcart.view.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class login_main : Fragment() {

    lateinit var loginBtn:AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //navController = Navigation.findNavController(view)
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login_main, container, false)
        loginBtn = view.findViewById(R.id.loginBtn)

        loginBtn.setOnClickListener {
            //navController.navigate(R.id.action_login_main_to_home_Page)
            it.findNavController().navigate(R.id.action_login_main_to_home_Page)
        }
        //(activity as? MainActivity)?.setBottomNavigationVisibility(false)
        return view
    }


}