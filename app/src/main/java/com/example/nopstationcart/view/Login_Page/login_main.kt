package com.example.nopstationcart.view.Login_Page

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.nopstationcart.R
import com.example.nopstationcart.databinding.FragmentLoginMainBinding

class login_main : Fragment(R.layout.fragment_login_main) {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginMainBinding.bind(view)
        getApiResponse(view)
        guestUser()
    }
    private fun guestUser(){
        binding.guestUser.setOnClickListener {
            val action = login_mainDirections.actionLoginMainToHomePage()
            findNavController().navigate(action)
        }
    }

    private fun getApiResponse(view: View) {
        var userName = ""
        binding.loginBtn.setOnClickListener {
            userName = binding.userName.text.toString().trim()
            val userPassword = binding.userPassword.text.toString().trim()
            if (userName.isNotEmpty() && userPassword.isNotEmpty()) {
                val sharedpreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
                val editor = sharedpreferences.edit()
                println("Email From login page : $userName")
                editor.putString("Email",userName)
                editor.apply()
                binding.progressBar.visibility = View.VISIBLE
                loginViewModel.login(userName, userPassword)
            } else {
                Toast.makeText(requireContext(), "Please enter both username and password", Toast.LENGTH_LONG).show()
            }
        }

        loginViewModel.loginResult.observe(viewLifecycleOwner) { result ->
            result.onSuccess {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_LONG).show()
                loginViewModel.saveToken(requireContext(), it.Data.Token)

                val sharedpreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
                val user = sharedpreferences.getString("User", null)

                if(user=="Guest"){
                    with(sharedpreferences.edit()) {
                        remove("User")
                        apply()
                    }
                    val action = login_mainDirections.actionLoginMainToCheckoutMain()
                    findNavController().navigate(action)
                }else{
                    view.findNavController().navigate(R.id.action_login_main_to_home_Page)

                }
            }.onFailure {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), "Login Failed . Please Provide Valid Credentials", Toast.LENGTH_LONG).show()
            }
        }
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_main, container, false)
    }
}
