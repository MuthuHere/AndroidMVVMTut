package com.muthu.androidmvvm.ui.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.muthu.androidmvvm.data.repository.UserRepository
import com.muthu.androidmvvm.ui.appinterface.AuthInterface
import com.muthu.androidmvvm.util.Coroutines
import com.muthu.androidmvvm.util.NoInternetExceptions

class AuthViewModel(
    private val userRepository: UserRepository
) : ViewModel() {


    var email: String? = null
    var password: String? = null

    var authListener: AuthInterface? = null


    fun onLoginClicked(view: View) {

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {

            authListener?.onFailed("Invalid username or password")
            return
        }
        authListener?.onStarted()

        Coroutines.main {
            try {
                val authResponse = userRepository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    userRepository.insertIntoDb(it)

                    return@main
                }
                authListener?.onFailed(authResponse.message!!)
            } catch (e: NoInternetExceptions) {

                authListener?.onFailed(e.message!!)
            }


        }

    }

    fun getUserData() = userRepository.getUser()
}