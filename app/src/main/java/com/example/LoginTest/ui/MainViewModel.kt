package com.example.LoginTest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clase1.R
import com.example.LoginTest.data.LoginRepository
import com.example.LoginTest.data.success
import com.example.LoginTest.domain.TryLoginRepositoryUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel (
    private val tryLoginRepositoryUseCase: TryLoginRepositoryUseCase = TryLoginRepositoryUseCase()
        ): ViewModel () {

    private val _state= MutableLiveData<UiState>()
    val state: LiveData<UiState> get() = _state

    //private val loginRepository = LoginRepository()


    /* OPCION B PARA USAR MUTABLE LIVE DATA
    sealed class UiStateOld{
        object Loading: UiStateOld()
        data class Error(val userError: String, val passError: String) : UiStateOld():
        object LoggedIn: UiStateOld()
    }*/

    data class UiState (
        val loggingIn: Boolean = false,
        val loggedIn: Boolean = false,
        val userError: Int? = null,
        val passError: Int? = null
            )

    fun onTryLogin(user: String, pass: String){
        viewModelScope.launch {
           _state.value = UiState(loggingIn = true)
            val result = tryLoginRepositoryUseCase(user, pass)
            _state.value = UiState(
                userError = if (result.userError) R.string.user_error else null,
                passError = if (result.passError)  R.string.pass_error else null,
                loggedIn = result.success

            )
        }
    }



    fun onNavigateToNextScreen() {
        _state.value = requireNotNull(_state.value).copy(loggedIn = false)

    }


}