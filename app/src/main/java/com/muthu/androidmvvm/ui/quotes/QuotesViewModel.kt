package com.muthu.androidmvvm.ui.quotes

import androidx.lifecycle.ViewModel;
import com.muthu.androidmvvm.data.repository.QuotesRepository
import com.muthu.androidmvvm.util.lazyDeferred

class QuotesViewModel(
    private val repository: QuotesRepository
) : ViewModel() {


    val quotes by lazyDeferred {

        repository.getQuotes()
    }

}
