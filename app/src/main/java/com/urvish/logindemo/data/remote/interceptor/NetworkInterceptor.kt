package com.urvish.logindemo.data.remote.interceptor

import android.content.Context
import com.urvish.logindemo.R
import com.urvish.logindemo.data.remote.exceptions.NetworkNotFoundException
import com.urvish.logindemo.utility.NetworkUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkInterceptor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (NetworkUtils.hasNetwork(context)) {
            return chain.proceed(request)
        }
        throw NetworkNotFoundException(context.getString(R.string.msg_no_internet))
    }
}