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
            userError = (!username.contains('@') && !username.contains(".com")),
            passError = (password.length < 6
                    && (
                            !password.contains('1') ||
                            !password.contains('2') ||
                            !password.contains('3') ||
                            !password.contains('4') ||
                            !password.contains('5') ||
                            !password.contains('6') ||
                            !password.contains('7') ||
                            !password.contains('8') ||
                            !password.contains('9')
                    )
                    && (
                            !password.contains('!') ||
                            !password.contains('#') ||
                            !password.contains('$') ||
                            !password.contains('%') ||
                            !password.contains('/') ||
                            !password.contains('=') ||
                            !password.contains('.')
                    ))

        )
    }

}