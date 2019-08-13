package com.muthu.androidmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muthu.androidmvvm.data.db.entities.CURRENT_USER_ID
import com.muthu.androidmvvm.data.db.entities.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM User WHERE uid = $CURRENT_USER_ID")
     fun getUser(): LiveData<User>
}