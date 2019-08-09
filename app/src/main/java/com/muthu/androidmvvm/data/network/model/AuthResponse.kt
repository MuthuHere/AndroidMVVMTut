package com.muthu.androidmvvm.data.network.model

import com.muthu.androidmvvm.data.db.entities.User

data class AuthResponse(
    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?
)