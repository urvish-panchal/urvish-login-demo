package com.urvish.logindemo.data.remote.retrofit

import android.content.Context
import com.urvish.logindemo.BuildConfig
import com.urvish.logindemo.data.remote.interceptor.NetworkInterceptor
import com.urvish.logindemo.data.remote.interceptor.RequestInterceptor
import okhttp3.OkHttpClient

import java.util.concurrent.TimeUnit

abstract class CommonRetrofit<T>(private val context: Context) : BaseRetrofit<T>() {

    override val baseUrl: String
        get() = BuildConfig.BASE_URL

    override fun okHttpClientHandler(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        builder.connectTimeout(60, TimeUnit.SECONDS)
        builder.readTimeout(60, TimeUnit.SECONDS)
        builder.writeTimeout(60, TimeUnit.SECONDS)
        builder.addInterceptor(NetworkInterceptor(context))
        builder.addInterceptor(RequestInterceptor(context))
        return super.okHttpClientHandler(builder)
    }
}