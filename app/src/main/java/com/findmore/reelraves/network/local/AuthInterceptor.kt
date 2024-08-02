package com.findmore.reelraves.network.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AuthInterceptor @Inject constructor(@ApplicationContext private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val token = TokenManager.getToken()
        val builder = originalRequest.newBuilder()

        if (token != null) {
            builder.addHeader("Authorization", "Bearer $token")
        }

        val newRequest = builder.build()
        return chain.proceed(newRequest)
    }
}
