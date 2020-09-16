package com.urvish.logindemo.data

import android.content.Context
import com.urvish.logindemo.data.local.database.XaccData
import com.urvish.logindemo.data.remote.login.LoginEntityData
import com.urvish.logindemo.data.remote.login.model.LoginRequest
import com.urvish.logindemo.data.remote.retrofit.BaseApi
import com.urvish.logindemo.data.remote.retrofit.CommonRetrofit
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response

class RemoteLoginEntityData(private val context: Context) : CommonRetrofit<BaseApi>(context),
    LoginEntityData {

    override val restClass: Class<BaseApi>
        get() = BaseApi::class.java

    private fun <T> applyServiceTransformer(): FlowableTransformer<T, T> {
        return FlowableTransformer { upstream ->
            upstream.compose { upstream ->
                upstream.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
            }

        }  //Todo Need to identify API failed Here on error
    }

    override fun postLogin(name: String, password: String): Flowable<Response<ResponseBody>>? {
        return getNetworkService()!!.postLogin(LoginRequest(name, password))
            .compose(applyServiceTransformer())
    }

    override fun saveUserXaccToLocal(xaccData: XaccData) {}
}