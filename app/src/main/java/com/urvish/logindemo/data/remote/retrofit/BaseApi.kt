package com.urvish.logindemo.data.remote.retrofit

import com.urvish.logindemo.data.remote.login.model.LoginRequest
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface BaseApi {

    @POST("login")
    fun postLogin(
        @Body loginRequest: LoginRequest
    ): Flowable<Response<ResponseBody>>
}