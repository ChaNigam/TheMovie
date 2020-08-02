package com.kotlin.training.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.kotlin.training.R


class LoginViewModel : ViewModel() {

    val name: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val loggedIn: MutableLiveData<Boolean> = MutableLiveData()

    fun login( name: String?,
               password: String?) {
        loggedIn.value =  isUsernameAndPasswordValid(name, password)

    }

    private fun isUsernameAndPasswordValid(
        name: String?,
        password: String?
    ): Boolean { // validate name and password
        if ((name?.trim().isNullOrBlank())) {
            errorMessage.value = R.string.snack_username
            return false
        } else if (password?.trim().isNullOrBlank() || (password != null && password.length < 6)) {
            errorMessage.value = R.string.snack_password
            return false
        } else {
            errorMessage.value = null
            return true
        }

    }

}