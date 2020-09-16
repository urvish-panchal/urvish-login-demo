package com.urvish.logindemo.data.remote.login

import com.urvish.logindemo.data.local.database.XaccData
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Response

interface LoginEntityData {

    fun postLogin(name: String, password: String): Flowable<Response<ResponseBody>>?

    fun saveUserXaccToLocal(data: XaccData)
}