package com.muthu.androidmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muthu.androidmvvm.data.db.entities.Quote

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllQuotes(quote: List<Quote>)

    @Query("SELECT * FROM Quote")
    fun getAllQuotes(): LiveData<List<Quote>>
}