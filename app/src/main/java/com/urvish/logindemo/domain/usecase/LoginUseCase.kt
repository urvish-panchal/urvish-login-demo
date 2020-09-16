package com.urvish.logindemo.domain.usecase

import com.urvish.logindemo.domain.UseCase
import com.urvish.logindemo.domain.model.LoginData
import com.urvish.logindemo.domain.repository.LoginRepository
import io.reactivex.Flowable

import javax.inject.Inject

class LoginUseCase @Inject
constructor(private val loginRepository: LoginRepository) :
    UseCase<LoginData, LoginUseCase.Params>() {

    class Params(val username: String, val password: String)

    override fun buildUseCaseFlowable(params: Params?): Flowable<LoginData> {
        return loginRepository.postLogin(params!!.username, params.password)
    }
}