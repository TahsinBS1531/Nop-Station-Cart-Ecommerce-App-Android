package com.example.nopstationcart.view.Account

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.nopstationcart.R
import com.example.nopstationcart.databinding.FragmentLogOutMainBinding
import com.example.nopstationcart.viewmodel.AccountInfoViewModel
import com.example.nopstationcart.viewmodel.LogOutViewModel

class logOutMain : Fragment() {

    private lateinit var binding: FragmentLogOutMainBinding
    private val logOutViewModel: LogOutViewModel by viewModels()
    private val accountInfo:AccountInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_log_out_main, container, false)
        binding = FragmentLogOutMainBinding.bind(view)
        handleAccountInfo()
        handleLogOut()
        return binding.root
    }

    private fun handleAccountInfo(){
        accountInfo.getAccountResponse(requireContext())
        accountInfo.result.observe(viewLifecycleOwner){response->
            response.onSuccess {
                binding.accountPageName.text = "${it.Data.FirstName} ${it.Data.LastName}"
                binding.accountPageEmail.text = it.Data.Email
                binding.AccountInfoPhone.text = "Phone Number: ${it.Data.Phone}"
                binding.AccountInfoCompany.text = "Company Name: ${it.Data.Company}"
                binding.AccountInfoGender.text = "Gender : ${it.Data.Gender}"
                binding.AccountIntoDOB.text = "Date of Birth : ${it.Data.DateOfBirthDay}/${it.Data.DateOfBirthMonth}/${it.Data.DateOfBirthYear}"
            }.onFailure {

            }
        }
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
                    remove("Email")
                    apply()
                }

                val token = sharedpreferences.getString("TOKEN", null)
                val email = sharedpreferences.getString("Email", null)
                println("Email after successful log out: $email")

                Toast.makeText(requireContext(), "Log Out Successful", Toast.LENGTH_LONG).show()
                //val action = logOutMainDirections.actionLogOutMainToLoginMain()
                findNavController().navigate(R.id.login_main)
            }.onFailure {
                Toast.makeText(requireContext(), "Log Out failed", Toast.LENGTH_LONG).show()
            }
        }
    }


}