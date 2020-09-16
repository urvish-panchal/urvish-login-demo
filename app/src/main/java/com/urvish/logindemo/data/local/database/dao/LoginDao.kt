package com.urvish.logindemo.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.urvish.logindemo.data.local.database.XaccData

@Dao
interface LoginDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertXaccData(xaccData: XaccData): Long
}