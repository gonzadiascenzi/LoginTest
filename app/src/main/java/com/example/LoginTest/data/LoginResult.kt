package com.example.LoginTest.data

data class LoginResult(val userError: Boolean, val passError: Boolean)

val LoginResult.success get() = !userError && !passError
