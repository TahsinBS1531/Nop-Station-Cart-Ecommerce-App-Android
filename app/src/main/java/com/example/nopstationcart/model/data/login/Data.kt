package com.example.nopstationcart.model.data.login

data class Data(
    val CheckoutAsGuest: Boolean,
    val DisplayCaptcha: Boolean,
    val Email: String,
    val Password: String,
    val RegistrationType: Int,
    val RememberMe: Boolean,
    val UsernamesEnabled: Boolean,
    val username: String
)