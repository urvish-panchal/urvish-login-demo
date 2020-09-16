package com.urvish.logindemo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.urvish.logindemo.data.local.database.dao.LoginDao

@Database(
    entities = [XaccData::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract val loginDao: LoginDao
}