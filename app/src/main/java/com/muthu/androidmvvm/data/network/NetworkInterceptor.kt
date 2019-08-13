package com.muthu.androidmvvm.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.muthu.androidmvvm.util.NoInternetExceptions
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor
    (context: Context) : Interceptor {

    private val context = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {

        if (!isInternetAvailable()) {
            throw NoInternetExceptions("No internet Available!")
        }

        return chain.proceed(chain.request())
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager = this.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.activeNetworkInfo.also {

            return it != null && it.isConnected
        }
    }
}