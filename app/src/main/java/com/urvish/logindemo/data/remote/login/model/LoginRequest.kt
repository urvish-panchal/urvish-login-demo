package com.urvish.logindemo.data.remote.login.model

import com.google.gson.annotations.SerializedName

class LoginRequest(
    
    @field:SerializedName("username")
    var userName: String?,

    @field:SerializedName("password")
    var pwd: String?
)