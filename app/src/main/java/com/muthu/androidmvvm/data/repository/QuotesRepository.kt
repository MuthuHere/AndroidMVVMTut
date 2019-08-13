package com.muthu.androidmvvm.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.muthu.androidmvvm.data.db.AppDatabse
import com.muthu.androidmvvm.data.db.entities.Quote
import com.muthu.androidmvvm.data.network.MyApi
import com.muthu.androidmvvm.data.network.SafeApiResponse
import com.muthu.androidmvvm.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuotesRepository(
    private val api: MyApi,
    private val db: AppDatabse
) : SafeApiResponse() {

    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }

    }

    suspend fun getQuotes(): LiveData<List<Quote>> {

        return withContext(Dispatchers.IO){
            fetchQuotes()
            db.getQuoteDao().getAllQuotes()
        }
    }


    private suspend fun fetchQuotes() {
        if (isFetchNeeded()) {
            val data = apiRequest {
                api.getQuotes()
            }

            quotes.postValue(data.quotes)
        }
    }

    private fun isFetchNeeded(): Boolean {
        return true
    }

    private fun saveQuotes(quotes: List<Quote>?) {

        Coroutines.io {
            db.getQuoteDao().saveAllQuotes(quotes!!)
        }

    }
}