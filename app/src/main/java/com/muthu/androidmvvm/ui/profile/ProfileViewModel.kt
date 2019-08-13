package com.muthu.androidmvvm.ui.profile

import androidx.lifecycle.ViewModel;
import com.muthu.androidmvvm.data.repository.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {



    val user  = repository.getUser()
}
