package com.android.myrecipes.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.myrecipes.R
import com.android.myrecipes.model.RecipeData
import com.squareup.picasso.Picasso

class RecipeListAdapter(recipesList:List<RecipeData>) :
    ListView<RecipeListAdapter.RecipesListHolder>()
    {
        lateinit var context: Context
        private val myRecipesList: List<RecipeData> = recipesList
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecipesListHolder {
            context = parent.context
            val inflater = LayoutInflater.from(context)
            return RecipesListHolder(inflater.inflate(R.layout.recipe_item, parent, false))
        }

        override fun onBindViewHolder(holder: RecipesListHolder, position: Int) {
            val recipe = myRecipesList[position]
            holder.name.text = recipe.name
            holder.fats.text = recipe.fats
            holder.calories.text = recipe.calories
           // Picasso.get().load(recipe.image).into(holder.recipeImage)
        }
        class RecipesListHolder(view: View) : RecyclerView.ViewHolder(view) {
            val name: TextView = view.findViewById(R.id.name_textView)
            val calories: TextView = view.findViewById(R.id.calories_textView)
            val fats: TextView = view.findViewById(R.id.fats_textView)
            val recipeImage: ImageView = view.findViewById(R.id.recipe_list_imageView)
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItemViewType(position: Int): Int {
            return position
        }
        override fun getItemCount(): Int {
            return myRecipesList.size
        }
    }

