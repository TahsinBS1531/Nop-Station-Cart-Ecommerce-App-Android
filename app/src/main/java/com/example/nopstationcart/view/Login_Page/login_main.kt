package com.example.nopstationcart.view.Login_Page

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.nopstationcart.R
import com.example.nopstationcart.databinding.FragmentLoginMainBinding
import com.example.nopstationcart.viewmodel.LoginViewModel

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
    }

    private fun getApiResponse(view: View) {
        binding.loginBtn.setOnClickListener {
            val userName = binding.userName.text.toString().trim()
            val userPassword = binding.userPassword.text.toString().trim()
            loginViewModel.login(userName, userPassword)
        }

        loginViewModel.loginResult.observe(viewLifecycleOwner) { result ->
            result.onSuccess {
                Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_LONG).show()
                loginViewModel.saveToken(requireContext(), it.Data.Token)
                view.findNavController().navigate(R.id.action_login_main_to_home_Page)
            }.onFailure {
                Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_LONG).show()
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
