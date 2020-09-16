package com.urvish.logindemo.data.local.database

import android.content.Context
import androidx.room.Room

abstract class BasePersistence(private val context: Context) {
    private var mDatabase: LocalDatabase? = null

    val db: LocalDatabase?
        get() {
            if (mDatabase == null) {
                initDatabase()
            }
            return mDatabase
        }

    private fun initDatabase() {
        mDatabase = Room.databaseBuilder(context, LocalDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    companion object {
        private val DB_NAME = "MY_LOCAL_DB.db"
    }
}