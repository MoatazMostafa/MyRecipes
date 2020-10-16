package com.android.myrecipes.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.android.myrecipes.R
import com.android.myrecipes.controller.Repository
import com.android.myrecipes.model.RecipeData
import com.android.myrecipes.view.adapters.RecipeListAdapter
import kotlinx.android.synthetic.main.activity_recipe_list.*
import java.io.Serializable

class RecipeListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)
        var recipesList=ArrayList<RecipeData>()
        val repository= Repository(this.application)
        var disposableData= repository.getRecipesList() //Observe Data From Repository
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response->
                    if(response.isNotEmpty()) {
                        showList(response) // Send Recipes list to adapter
                        recipesList=response as ArrayList<RecipeData>
                    }
                },
                {
                    val t=Toast.makeText(this,"Connection Error",Toast.LENGTH_SHORT)
                    t.show()
                })

        search_editText!!.addTextChangedListener(object : TextWatcher { //Update list when user search by name
            override fun afterTextChanged(s: Editable?) {
                if (s!!.isNotEmpty() && recipesList.isNotEmpty()) {
                    val filteredList = recipesList.filter {
                        it.name!!.contains(s, true)
                    }
                    showList(filteredList) //When entered name match item/s in list send result list to adapter
                }else
                    showList(recipesList) //When search field is empty send original list to adapter
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }
    private fun showList(recipeList: List<RecipeData>) {
        val adapter = RecipeListAdapter(this, recipeList)
        recipes_list_lv.adapter = adapter
        adapter.itemSelected={ // Receive selected recipe and star RecipeDetailsActivity
            val intent = Intent(applicationContext, RecipeDetailsActivity::class.java)
            intent.putExtra("recipe", it as Serializable)
            startActivity(intent)
        }
    }
}