package com.android.myrecipes.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.myrecipes.R
import com.android.myrecipes.model.RecipeData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_recipe_details.*

class RecipeDetailsActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)
        val recipe=   intent.getSerializableExtra("recipe") as RecipeData
        name_textView.text= "${name_textView.text} : ${recipe.name}"
        headline_textView.text= "${headline_textView.text} : ${recipe.headline}"
        calories_textView.text= "${calories_textView.text} : ${recipe.calories}"
        carbos_textView.text= "${carbos_textView.text} : ${recipe.carbos}"
        fats_textView.text= "${fats_textView.text} : ${recipe.fats}"
        proteins_textView.text= "${proteins_textView.text} : ${recipe.proteins}"
        difficulty_textView.text= "${difficulty_textView.text} : ${recipe.difficulty}"
        time_textView.text= "${time_textView.text} : ${recipe.time}"
        description_textView.text= "${description_textView.text} : ${recipe.description}"
        Picasso.get().load(recipe.image).into(recipe_imageView)
    }
}