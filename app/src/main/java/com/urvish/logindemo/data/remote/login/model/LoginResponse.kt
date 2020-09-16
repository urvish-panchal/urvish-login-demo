package com.urvish.logindemo.data.remote.login.model

import com.google.gson.annotations.SerializedName

class LoginResponse {
    
    @SerializedName("errorMessage")
    var errorMessage: String? = null

    @SerializedName("errorCode")
    var errorCode: Int = -1
}