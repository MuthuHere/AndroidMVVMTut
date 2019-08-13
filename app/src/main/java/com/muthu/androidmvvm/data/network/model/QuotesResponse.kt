package com.muthu.androidmvvm.data.network.model

import com.muthu.androidmvvm.data.db.entities.Quote

data class QuotesResponse(
    val isSuccessful: Boolean,
    val quotes: List<Quote>
)