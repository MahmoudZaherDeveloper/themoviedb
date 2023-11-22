package com.zaher.cleanwithmvvm.data.source.remote

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newUrl = originalRequest.url
            .newBuilder()
            .addQueryParameter(
                "api_key",
                "1959301b68a4afd398706087243bd824"
            )
            .build()
        val request = originalRequest.newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(request)
    }
}