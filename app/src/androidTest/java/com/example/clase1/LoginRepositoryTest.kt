package com.example.clase1

import com.example.LoginTest.data.LoginRemoteDataSource
import com.example.LoginTest.data.LoginRemoteDataSourceImplementation
import com.example.LoginTest.data.LoginRepository
import com.example.LoginTest.data.LoginResult
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class LoginRepositoryTest { //Testeando una clase que depende de otras

    private lateinit var ds: FakeLoginRemoteDS
    private lateinit var repository: LoginRepository

    @Before
    fun setUp(){
        ds = FakeLoginRemoteDS()
        repository = LoginRepository(ds)

    }



    @Test
    fun checkErrorsArePassedToTheRepository(): Unit = runBlocking{
        ds.userError = true
        ds.passError = true
        val result = repository.tryLogin("","")
        assertTrue(result.userError)
        assertTrue(result.passError)
    }

}

class FakeLoginRemoteDS (var userError: Boolean = false, var passError: Boolean=false):
    LoginRemoteDataSource {

    override suspend fun tryLogin(username: String, password: String): LoginResult {
        return LoginResult(userError, passError)
    }

}