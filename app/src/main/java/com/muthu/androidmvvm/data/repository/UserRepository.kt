package com.muthu.androidmvvm.data.repository

import com.muthu.androidmvvm.data.db.AppDatabse
import com.muthu.androidmvvm.data.db.entities.User
import com.muthu.androidmvvm.data.network.MyApi
import com.muthu.androidmvvm.data.network.SafeApiResponse
import com.muthu.androidmvvm.data.network.model.AuthResponse

class UserRepository(
    private val api: MyApi,
    private val database: AppDatabse
) : SafeApiResponse() {


    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest {
            api.userLogin(email, password)
        }
    }

    suspend fun userSignUp(email: String, password: String, name: String): AuthResponse {

        return apiRequest {
            api.userSignUp(email, password, name)
        }
    }


    suspend fun insertIntoDb(user: User) = database.getUserDao().insert(user)

    fun getUser() = database.getUserDao().getUser()


}