package com.android.myrecipes.controller.services

import com.android.myrecipes.controller.Constants.BASE_URL
import com.android.myrecipes.model.RecipeData
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ClientService {
    companion object {
        fun getClient(): ClientService {
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