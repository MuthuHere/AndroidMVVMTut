package com.muthu.androidmvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.muthu.androidmvvm.data.db.entities.Quote
import com.muthu.androidmvvm.data.db.entities.User

@Database(
    entities = [User::class, Quote::class],
    version = 1
)
abstract class AppDatabse : RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getQuoteDao(): QuoteDao


    companion object {

        @Volatile
        private var instance: AppDatabse? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }


        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabse::class.java,
                "MyDatabase.db"
            ).build()

    }
}