package com.example.testworkmate.common.retrofit

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

const val TAG = "LoggingInterceptor"

class LoggingInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val path = chain.request().url.encodedPath

        Log.d(TAG, path)

        return chain.proceed(chain.request())
    }

}