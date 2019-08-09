package com.muthu.androidmvvm.ui.appinterface

import androidx.lifecycle.LiveData

interface AuthInterface {
    fun onStarted()
    fun onSuccess(message: LiveData<String>)
    fun onFailed(message: String)
}