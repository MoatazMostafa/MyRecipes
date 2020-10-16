package com.android.myrecipes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.android.myrecipes.R
import com.android.myrecipes.controller.Repository
import com.android.myrecipes.model.RecipeData
import com.android.myrecipes.view.adapters.RecipeListAdapter
import kotlinx.android.synthetic.main.activity_recipe_list.*

class RecipeListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)
        val repository= Repository(this.application)
        get_recipes_button.setOnClickListener {
            var disposableData= repository.getRecipesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response->
                        if(response.isNotEmpty())
                        showList(response)
                    },
                    {
                        val t=Toast.makeText(this,"Connection Error",Toast.LENGTH_SHORT)
                        t.show()
                    })
        }
    }
    private fun showList(recipeList: List<RecipeData>) {
        val adapter = RecipeListAdapter(this, recipeList)
        recipes_list_lv.adapter = adapter
    }
}