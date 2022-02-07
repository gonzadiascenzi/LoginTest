package com.example.clase1

import com.example.LoginTest.data.LoginRemoteDataSource
import com.example.LoginTest.data.LoginRemoteDataSourceImplementation
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class LoginRemoteDataSourceTest { //Testeando clases individuales

    private val ds = LoginRemoteDataSourceImplementation();

    @Test
    fun UserWithOutArrobaReturnError() = runBlocking { //Hasta que no acaben las funciones suspend no acaben esta acction no termina
        val result = ds.tryLogin("user","")
        assertTrue(result.userError)
    }

    @Test
    fun userWithArrobaReturnSuccess() = runBlocking {
        val result = ds.tryLogin("user@","")
        assertFalse(result.userError)
    }

    @Test
    fun passWithLessThan5CharactersError() = runBlocking {
        val result = ds.tryLogin("","1234")
        assertTrue(result.passError)
    }

    @Test
    fun passWith5CharactersSuccess() = runBlocking {
        val result = ds.tryLogin("","12345")
        assertFalse(result.passError)
    }
}