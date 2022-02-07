package com.example.LoginTest.domain

import com.example.LoginTest.data.LoginRepository
import com.example.LoginTest.data.LoginResult

class TryLoginRepositoryUseCase (
    private val loginRepository: LoginRepository = LoginRepository()
) {
    suspend operator fun invoke (user: String, pass:String): LoginResult =
        loginRepository.tryLogin(user, pass)

}