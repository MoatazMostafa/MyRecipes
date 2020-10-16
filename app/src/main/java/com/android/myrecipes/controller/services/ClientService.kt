package com.android.myrecipes.controller.services

import android.app.Application
import com.android.myrecipes.model.RecipeData
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ClientService {
    companion object {
        var BASE_URL ="https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/" //API
        fun getClient(application: Application): ClientService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(HttpClient.getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return retrofit.create(ClientService::class.java)
        }
    }
    @GET("recipes.json")
    fun getRecipes(): Observable<List<RecipeData>>
}