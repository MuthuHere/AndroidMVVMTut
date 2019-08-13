package com.muthu.androidmvvm.ui.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.muthu.androidmvvm.data.repository.QuotesRepository


@Suppress("UNCHECKED_CAST")
class QuotesViewModelFactory(
   private val repository:QuotesRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(repository) as T

    }
}