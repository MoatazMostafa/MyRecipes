package com.android.myrecipes.controller

import android.app.Application
import com.android.myrecipes.controller.services.ClientService
import com.android.myrecipes.model.RecipeData
import io.reactivex.Observable
import retrofit2.Call

class Repository(application: Application) {
    private var clientService = ClientService.getClient(application)

    fun getRecipesList(): Observable<List<RecipeData>> { //Get recipes from API
       return clientService.getRecipes()
    }

}