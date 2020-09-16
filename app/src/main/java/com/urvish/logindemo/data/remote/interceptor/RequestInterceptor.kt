package com.urvish.logindemo.data.remote.interceptor

import android.content.Context
import com.urvish.logindemo.utility.Constants
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RequestInterceptor(context: Context) : Interceptor {
    var IMSI = "357175048449937"
    var IMEI = "510110406068589"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()

        // TODO : This value will be change based on requirements.
        builder.addHeader(Constants.KEY_HEADER_IMSI, IMSI)
        builder.addHeader(Constants.KEY_HEADER_IMEI, IMEI)

        val request = builder.method(original.method, original.body).build()
        return chain.proceed(request)
    }
}