package com.example.LoginTest.data

import com.example.clase1.R
import com.example.LoginTest.ui.MainViewModel
import kotlinx.coroutines.delay

class LoginRepository (
    private val loginRemoteDataSource: LoginRemoteDataSource = LoginRemoteDataSourceImplementation()
) {

    suspend fun tryLogin (username: String, password: String): LoginResult =
        loginRemoteDataSource.tryLogin(username, password)


}

