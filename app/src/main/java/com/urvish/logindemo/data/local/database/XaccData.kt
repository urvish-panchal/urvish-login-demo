package com.urvish.logindemo.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class XaccData {

    @PrimaryKey
    lateinit var userName: String

    var xacc: String? = null
}