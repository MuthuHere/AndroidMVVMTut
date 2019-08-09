package com.muthu.androidmvvm.ui.auth

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.muthu.androidmvvm.R
import com.muthu.androidmvvm.databinding.ActivityLoginBinding
import com.muthu.androidmvvm.ui.appinterface.AuthInterface
import com.muthu.androidmvvm.ui.viewmodel.AuthViewModel
import com.muthu.androidmvvm.util.hide
import com.muthu.androidmvvm.util.show
import com.muthu.androidmvvm.util.toast

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.authListener = this

    }

    override fun onStarted() {
        pbLogin.show()
    }


    override fun onSuccess(message: LiveData<String>) {
        message.observe(this, Observer {
            pbLogin.hide()
            toast(it)
        })

    }


    override fun onFailed(message: String) {
        pbLogin.hide()
        toast(message)
    }

}
