package com.urvish.logindemo.presentation.login

import android.content.Context
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.urvish.logindemo.R
import com.urvish.logindemo.databinding.ActivityLoginBinding
import com.urvish.logindemo.domain.model.LoginData
import com.urvish.logindemo.domain.usecase.LoginUseCase
import io.reactivex.subscribers.ResourceSubscriber
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    internal var mutableLoginLiveData = MutableLiveData<String>()

    fun checkValidationAndCallLoginWs(
        context: Context,
        binding: ActivityLoginBinding,
        username: String,
        password: String
    ) {
        if (checkValidation(context, binding, username, password)) {
            loginUseCase.executeFlowable(object : ResourceSubscriber<LoginData>() {
                override fun onNext(loginData: LoginData) {
                    mutableLoginLiveData.postValue(loginData.errorMessage)
                }

                override fun onError(t: Throwable) {
                    mutableLoginLiveData.postValue(t.localizedMessage)
                }

                override fun onComplete() {

                }
            }, LoginUseCase.Params(username, password))
        }
    }

    private fun checkValidation(
        context: Context,
        binding: ActivityLoginBinding,
        emailId: String,
        password: String
    ): Boolean {
        if (TextUtils.isEmpty(emailId)) {
            mutableLoginLiveData.postValue(context.getString(R.string.error_username_required))
            binding.tnlUsername.error = context.getString(R.string.error_username_required)
            binding.tnlUsername.isErrorEnabled = true
            return false
        } else if (TextUtils.isEmpty(password)) {
            mutableLoginLiveData.postValue(context.getString(R.string.error_password_required))
            binding.tnlUsername.error = null
            binding.tnlUsername.isErrorEnabled = false
            binding.tnlPassword.error = context.getString(R.string.error_password_required)
            binding.tnlPassword.isErrorEnabled = true
            return false
        }

        binding.tnlUsername.error = null
        binding.tnlUsername.isErrorEnabled = false
        binding.tnlPassword.error = null
        binding.tnlPassword.isErrorEnabled = false

        return true
    }
}