package com.muthu.androidmvvm.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.muthu.androidmvvm.R
import com.muthu.androidmvvm.data.db.entities.User
import com.muthu.androidmvvm.databinding.ActivitySignupBinding
import com.muthu.androidmvvm.ui.appinterface.AuthInterface
import com.muthu.androidmvvm.ui.home.HomeActivity
import com.muthu.androidmvvm.ui.viewmodel.AuthViewModel
import com.muthu.androidmvvm.ui.viewmodel.AuthViewModelFactory
import com.muthu.androidmvvm.util.hide
import com.muthu.androidmvvm.util.show
import com.muthu.androidmvvm.util.snackbar
import kotlinx.android.synthetic.main.activity_signup.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignUpActivity : AppCompatActivity(), AuthInterface, KodeinAware {


    override val kodein by kodein()

    private val factory: AuthViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding: ActivitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.authListener = this

        viewModel.getUserData().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

    }


    override fun onStarted() {
        pbSignUp.show()
    }


    override fun onSuccess(user: User?) {
        pbSignUp.hide()
        rootSignUp.snackbar(user?.name!!)
    }


    override fun onFailed(message: String) {
        pbSignUp.hide()
        rootSignUp.snackbar(message)
    }
}
