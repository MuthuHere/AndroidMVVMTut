package com.muthu.androidmvvm.ui.appinterface

import com.muthu.androidmvvm.data.db.entities.User

interface AuthInterface {
    fun onStarted()
    fun onSuccess(message: User?)
    fun onFailed(message: String)
}