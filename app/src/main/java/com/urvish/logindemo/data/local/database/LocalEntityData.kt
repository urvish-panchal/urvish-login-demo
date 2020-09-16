package com.urvish.logindemo.data.local.database

import android.content.Context
import com.urvish.logindemo.data.remote.login.LoginEntityData
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Response

class LocalEntityData(context: Context) : BasePersistence(context),
    LoginEntityData {

    override fun postLogin(name: String, password: String): Flowable<Response<ResponseBody>>? {
        return null
    }

    override fun saveUserXaccToLocal(data: XaccData) {
        db!!.loginDao.insertData(data)
    }
}