package com.urvish.logindemo.domain.repository

import com.urvish.logindemo.domain.model.LoginData
import io.reactivex.Flowable

interface LoginRepository {
    fun postLogin(name: String, password: String): Flowable<LoginData>
}