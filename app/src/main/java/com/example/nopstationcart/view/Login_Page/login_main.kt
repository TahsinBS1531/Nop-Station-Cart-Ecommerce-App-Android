package com.example.nopstationcart.view.Login_Page

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import com.example.nopstationcart.R
import com.example.nopstationcart.view.Home_Page.Home_Page
import com.example.nopstationcart.view.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class login_main : Fragment() {

    lateinit var loginBtn:AppCompatButton
    lateinit var bottomNav:BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login_main, container, false)
        loginBtn = view.findViewById(R.id.loginBtn)

        loginBtn.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer,Home_Page())
            transaction.commit()
        }
        (activity as? MainActivity)?.setBottomNavigationVisibility(false)
        return view
    }


}