package com.android.myrecipes.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.android.myrecipes.R
import com.android.myrecipes.controller.Constants.RECIPES_PASS
import com.android.myrecipes.controller.Constants.SORT_BY_CALORIES
import com.android.myrecipes.controller.Constants.SORT_BY_FATS
import com.android.myrecipes.controller.Constants.SORT_SHAREDPREFERENCES_NAME
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
        val repository= Repository()
        var lastSort:String
        val sharedPref=getSharedPreferences(SORT_SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE)
        sharedPref.apply {// get last sort choice
            lastSort =  getString(SORT_SHAREDPREFERENCES_NAME,"")!!
        }

        var disposableData= repository.getRecipesList() //Observe Data From Repository
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response->
                    if(response.isNotEmpty()) {
                        recipesList=response as ArrayList<RecipeData>
                        showList(recipesList,lastSort) // Send Recipes list to adapter
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
                    showList(filteredList,lastSort) //When entered name match item/s in list send result list to adapter
                }else
                    showList(recipesList,lastSort) //When search field is empty send original list to adapter
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        calories_floating_button.setOnClickListener {// Sort by calories and send sorted list to adapter
            showList(recipesList,SORT_BY_CALORIES)
            lastSort=SORT_BY_CALORIES
            val editor: SharedPreferences.Editor = sharedPref!!.edit()
            editor.putString(SORT_SHAREDPREFERENCES_NAME,SORT_BY_CALORIES)
            editor.apply()
            sort_floating_menu.close(true)
        }
        fats_floating_button.setOnClickListener {// Sort by fats and send sorted list to adapter
            showList(recipesList,SORT_BY_FATS)
            lastSort=SORT_BY_FATS
            val editor: SharedPreferences.Editor = sharedPref!!.edit()
            editor.putString(SORT_SHAREDPREFERENCES_NAME,SORT_BY_FATS)
            editor.apply()
            sort_floating_menu.close(true)
        }

    }

    private fun showList(recipeList: List<RecipeData>, sortBy:String) {
        val list = sortList(recipeList,sortBy)// sort list before viewing
        val adapter = RecipeListAdapter(this, list)
        recipes_list_lv.adapter = adapter
        adapter.itemSelected={ // Receive selected recipe and star RecipeDetailsActivity
            val intent = Intent(applicationContext, RecipeDetailsActivity::class.java)
            intent.putExtra(RECIPES_PASS, it as Serializable)
            startActivity(intent)
        }
    }

    private fun sortList(recipeList: List<RecipeData>,sortBy:String):List<RecipeData> {
        for(item in recipeList) {// Change any empty string to 0 for sorting
            if(item.calories!!.isEmpty())
                item.calories="0"
            if(item.fats!!.isEmpty())
                item.fats="0"
        }
        return when (sortBy) { //Filter field from any char and sort only by digit or decimal
            SORT_BY_CALORIES -> {
                recipeList.sortedBy { item->(item .calories?.filter { it.isDigit() || it == '.'})?.toInt()}
            }
            SORT_BY_FATS-> {
                recipeList.sortedBy { item->(item .fats?.filter { it.isDigit() || it == '.'})?.toInt()}
            }
            else -> recipeList
        }
    }
}