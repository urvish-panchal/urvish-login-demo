package com.urvish.logindemo.data.mapper

import com.google.gson.Gson
import com.urvish.logindemo.data.remote.login.model.LoginResponse
import com.urvish.logindemo.domain.model.LoginData
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class LoginMapper @Inject
constructor() {

    @Throws(IOException::class)
    fun transform(response: Response<ResponseBody>?): LoginData? {
        val loginResponse: LoginResponse
        if (response?.body() != null) {
            loginResponse = Gson().fromJson<Any>(
                response.body()!!.string(),
                LoginResponse::class.java
            ) as LoginResponse
            return transformLoginResponse(loginResponse)
        }
        return null;
    }

    private fun transformLoginResponse(loginResponse: LoginResponse): LoginData {
        val login = LoginData()
        login.errorMessage = loginResponse.errorMessage
        login.errorCode = loginResponse.errorCode
        return login
    }
}