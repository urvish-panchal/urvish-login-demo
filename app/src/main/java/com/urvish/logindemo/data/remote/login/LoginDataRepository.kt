package com.urvish.logindemo.data.remote.login

import android.content.Context
import com.urvish.logindemo.data.RemoteLoginEntityData
import com.urvish.logindemo.data.local.database.LocalEntityData
import com.urvish.logindemo.data.local.database.XaccData
import com.urvish.logindemo.data.mapper.LoginMapper
import com.urvish.logindemo.domain.model.LoginData
import com.urvish.logindemo.domain.repository.LoginRepository
import com.urvish.logindemo.utility.Constants
import io.reactivex.Flowable

import javax.inject.Inject

class LoginDataRepository @Inject constructor(
    private val mapper: LoginMapper,
    private val context: Context
) : LoginRepository {

    override fun postLogin(name: String, password: String): Flowable<LoginData> {
        return RemoteLoginEntityData(context).postLogin(name, password)!!
            .doOnNext { responseBodyResponse ->
                val data = XaccData()
                data.userName = name
                data.xacc = responseBodyResponse.headers()[Constants.KEY_HEADER_X_ACC]
                LocalEntityData(context).saveUserXaccToLocal(data)
            }.map { responseBodyResponse -> mapper.transform(responseBodyResponse) }
    }
}