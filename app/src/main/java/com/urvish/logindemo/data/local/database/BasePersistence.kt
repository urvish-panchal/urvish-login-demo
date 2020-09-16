package com.urvish.logindemo.data.local.database

import android.content.Context
import androidx.room.Room

abstract class BasePersistence(private val context: Context) {
    private var database: LocalDatabase? = null

    val db: LocalDatabase?
        get() {
            if (database == null) {
                initDatabase()
            }
            return database
        }

    private fun initDatabase() {
        database = Room.databaseBuilder(context, LocalDatabase::class.java!!, NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    companion object {
        private val NAME = "logintest.db"
    }
}