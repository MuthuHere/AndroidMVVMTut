package com.muthu.androidmvvm.data.repository

import com.muthu.androidmvvm.data.network.MyApi
import com.muthu.androidmvvm.data.network.SafeApiResponse
import com.muthu.androidmvvm.data.network.model.AuthResponse

class UserRepository :SafeApiResponse(){


    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest{
            MyApi().userLogin(email, password)
        }

    }


}