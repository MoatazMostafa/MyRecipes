package com.android.myrecipes.controller.services

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object HttpClient {
    fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }
}