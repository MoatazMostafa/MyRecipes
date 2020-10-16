package com.android.myrecipes.view.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.android.myrecipes.R
import com.android.myrecipes.model.RecipeData
import com.squareup.picasso.Picasso

class RecipeListAdapter(context: Context,recipesList:List<RecipeData>) : BaseAdapter() {
    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val myRecipesList: List<RecipeData> = recipesList

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return myRecipesList.size
    }
    override fun getItem(position: Int): Any {
        return myRecipesList[position]
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.recipe_item, parent, false)

        val name: TextView = rowView.findViewById(R.id.name_textView)
        val calories: TextView = rowView.findViewById(R.id.calories_textView)
        val fats: TextView = rowView.findViewById(R.id.fats_textView)
        val recipeImage: ImageView = rowView.findViewById(R.id.recipe_list_imageView)

        val recipe = getItem(position) as RecipeData
        name.text = recipe.name
        fats.text = recipe.fats
        calories.text = recipe.calories
        Picasso.get().load(recipe.image).into(recipeImage)

        return rowView
    }

    override fun getItemViewType(position: Int): Int {
            return position
        }
}

