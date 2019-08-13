package com.muthu.androidmvvm.ui.viewmodel

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.muthu.androidmvvm.data.repository.UserRepository
import com.muthu.androidmvvm.ui.appinterface.AuthInterface
import com.muthu.androidmvvm.ui.auth.SignUpActivity
import com.muthu.androidmvvm.util.APIExceptions
import com.muthu.androidmvvm.util.Coroutines
import com.muthu.androidmvvm.util.NoInternetExceptions

class AuthViewModel(
    private val userRepository: UserRepository
) : ViewModel() {


    var name: String? = null
    var email: String? = null
    var password: String? = null
    var confirmPassword: String? = null

    var authListener: AuthInterface? = null


    /**
     * signUp link clicked
     */
    fun onSingUp(view: View) {

        Intent(view.context, SignUpActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    /**
     * Login clicked
     */

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
            }catch (e : APIExceptions){
                authListener?.onFailed(e.message!!)
            }


        }

    }


    /**
     * sign~up clicked
     */
    fun onSignUpClicked(view: View) {


        if (name.isNullOrEmpty()) {
            authListener?.onFailed("Invalid User name!")
            return
        }
        if (email.isNullOrEmpty()) {
            authListener?.onFailed("Invalid Email!")
            return
        }
        if (password.isNullOrEmpty()) {
            authListener?.onFailed("Invalid Password!")
            return
        }
        if (confirmPassword.isNullOrEmpty()) {
            authListener?.onFailed("Confirm password cannot be empty!")
            return
        }
        authListener?.onStarted()

        Coroutines.main {
            try {
                val authResponse = userRepository.userSignUp(email!!, password!!,name!!)
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


    /**
     * get user data
     */
    fun getUserData() = userRepository.getUser()
}