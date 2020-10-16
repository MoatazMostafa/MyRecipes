package com.android.myrecipes.controller

import com.android.myrecipes.controller.services.ClientService
import com.android.myrecipes.model.RecipeData
import io.reactivex.Observable

class Repository{
    private var clientService = ClientService.getClient()

    fun getRecipesList(): Observable<List<RecipeData>> { //Get recipes from API
       return clientService.getRecipes()
    }

}