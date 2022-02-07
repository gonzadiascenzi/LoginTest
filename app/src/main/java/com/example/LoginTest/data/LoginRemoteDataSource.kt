package com.example.LoginTest.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

interface LoginRemoteDataSource {
    suspend fun tryLogin(username:String, password:String): LoginResult
}


class LoginRemoteDataSourceImplementation: LoginRemoteDataSource {

   override suspend fun tryLogin(username:String, password:String): LoginResult =
       withContext(Dispatchers.IO)
    {
        delay(2000)
        LoginResult(
            userError = !username.contains('@'),
            passError = password.length < 5
        )
    }

}