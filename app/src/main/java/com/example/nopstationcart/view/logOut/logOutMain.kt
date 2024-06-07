package com.example.nopstationcart.view.logOut

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.nopstationcart.R
import com.example.nopstationcart.databinding.FragmentLogOutMainBinding
import com.example.nopstationcart.databinding.HomePageFragmentBinding
import com.example.nopstationcart.view.Home_Page.Home_PageDirections
import com.example.nopstationcart.viewmodel.LogOutViewModel

class logOutMain : Fragment() {

    private lateinit var binding: FragmentLogOutMainBinding
    private val logOutViewModel: LogOutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_log_out_main, container, false)
        binding = FragmentLogOutMainBinding.bind(view)

        handleLogOut()
        return binding.root
    }


    private fun handleLogOut() {
        binding.mainLogOut.setOnClickListener {
            logOutViewModel.getCartResponse(requireContext())
        }

        logOutViewModel.result.observe(viewLifecycleOwner) { response ->
            response.onSuccess {
                val sharedpreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
                with(sharedpreferences.edit()) {
                    remove("TOKEN")
                    apply()
                }

                val token = sharedpreferences.getString("TOKEN", null)
                println("Token after successful log out: $token")

                Toast.makeText(requireContext(), "Log Out Successful", Toast.LENGTH_LONG).show()
                //val action = logOutMainDirections.actionLogOutMainToLoginMain()
                findNavController().navigate(R.id.login_main)
            }.onFailure {
                Toast.makeText(requireContext(), "Log Out failed", Toast.LENGTH_LONG).show()
            }
        }
    }


}