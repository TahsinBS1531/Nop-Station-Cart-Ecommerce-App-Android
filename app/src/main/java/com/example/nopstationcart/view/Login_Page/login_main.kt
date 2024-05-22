package com.example.nopstationcart.view.Login_Page

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.nopstationcart.R
import com.example.nopstationcart.model.data.login.Data
import com.example.nopstationcart.model.data.login.UploadPicture
import com.example.nopstationcart.model.data.login.loginAuthInterceptor
import com.example.nopstationcart.model.data.login.loginDataClass
import com.example.nopstationcart.model.data.login.loginResponse
import com.example.nopstationcart.model.interfaces.loginApiInterface
import com.example.nopstationcart.view.Home_Page.Home_Page
import com.example.nopstationcart.view.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class login_main : Fragment() {

    lateinit var loginBtn:AppCompatButton
    lateinit var userName:EditText
    lateinit var userPassword:EditText
    lateinit var FormValues: List<Any>
    lateinit var UploadPicture: UploadPicture


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

        userName = view.findViewById(R.id.userName)
        userPassword = view.findViewById(R.id.userPassword)


        loginBtn = view.findViewById(R.id.loginBtn)
        loginBtn.setOnClickListener {
            var userName1 = userName.text.toString().trim()
            var userPassword1 = userPassword.text.toString().trim()
            if(userName1.isNotEmpty() && userPassword1.isNotEmpty()){
                UploadPicture = UploadPicture()
                FormValues = listOf()
                val loginData = Data(false, false, userName1, userPassword1, 2, false, true,userName1)
                val loginRquest = loginDataClass(loginData,FormValues,UploadPicture)
                getMyData(loginRquest)

            }else{
                Toast.makeText(requireContext(),"Please Enter Your Username and password",Toast.LENGTH_LONG).show()
            }
        }

        return view
    }

    private fun getMyData(loginRequest:loginDataClass) {
        val BASE_URL = "http://demo460.nop-station.com/api/"
        val client = OkHttpClient.Builder()
            .addInterceptor(loginAuthInterceptor(
                deviceId = "44b4d8cd-7a2d-4a5f-a0e2-798021f1e294", // Provide actual device id
                nst = "eyJhbGciOiJIUzUxMiJ9.eyJOU1RfS0VZIjoiYm05d1UzUmhkR2x2YmxSdmEyVnUifQ.adqiIzFjqZdpJw5uHOHjE5qw2UvCDH2FwMmwlYvr5ljKyPG65ZQe_4wb8NYEQFXmyZZyVu-77xd5Njn310cjMw", // Provide actual NST token
                userAgent = "com.bs.ecommerce/1.0" // Provide actual user agent
            ))
            .build()

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(loginApiInterface::class.java)

        val retrofitData = retrofitBuilder.getApiData(loginRequest)
        retrofitData.enqueue(object : Callback<loginResponse?> {
            override fun onResponse(p0: Call<loginResponse?>, response: Response<loginResponse?>) {
                if(response.isSuccessful && response.body()!=null){
                    Toast.makeText(requireContext(),"Login Successful: ${response.message()} ",Toast.LENGTH_LONG).show()
                    println(response.body()!!.Data.Token)
                    saveToken(requireContext(), response.body()!!.Data.Token)
                    view?.findNavController()?.navigate(R.id.action_login_main_to_home_Page)
                }else{
                    Toast.makeText(requireContext(),"Login Unsuccessful: ${response.message()}",Toast.LENGTH_LONG).show()
                }

            }

            override fun onFailure(p0: Call<loginResponse?>, response: Throwable) {
                Toast.makeText(requireContext(),"Login unsuccessful failed ${response.message}",Toast.LENGTH_LONG).show()
                println(response.message)
            }
        })

        }

    private fun saveToken(context: Context, token: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("TOKEN", token)
        editor.apply()
    }

}