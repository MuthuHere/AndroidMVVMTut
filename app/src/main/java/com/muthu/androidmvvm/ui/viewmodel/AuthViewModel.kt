package com.muthu.androidmvvm.ui.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.muthu.androidmvvm.data.repository.UserRepository
import com.muthu.androidmvvm.ui.appinterface.AuthInterface

class AuthViewModel : ViewModel() {


    var email: String? = null
    var password: String? = null

    var authListener: AuthInterface? = null


    fun onLoginClicked(view: View) {

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {

            authListener?.onFailed("Invalid username or password")
            return
        }
        authListener?.onStarted()
        val response = UserRepository().userLogin(email!!, password!!)
        authListener?.onSuccess(response)
    }
}