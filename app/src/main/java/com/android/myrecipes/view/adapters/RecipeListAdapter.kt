package com.android.myrecipes.view.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.android.myrecipes.R
import com.android.myrecipes.model.RecipeData
import com.squareup.picasso.Picasso

class RecipeListAdapter(context: Context,recipesList:List<RecipeData>) : BaseAdapter() {
    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val myRecipesList: List<RecipeData> = recipesList
    var itemSelected: ((RecipeData) -> Unit)? = null

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

        val recipeCardView : ConstraintLayout = rowView.findViewById(R.id.recipes_listItem)
        val nameView: TextView = rowView.findViewById(R.id.name_listItem_textView)
        val caloriesView: TextView = rowView.findViewById(R.id.calories_listItem_textView)
        val fatsView: TextView = rowView.findViewById(R.id.fats_listItem_textView)
        val recipeImageView: ImageView = rowView.findViewById(R.id.recipe_listItem_imageView)

        val recipe = getItem(position) as RecipeData
        nameView.text = recipe.name
        fatsView.text = recipe.fats
        caloriesView.text = recipe.calories
        Picasso.get().load(recipe.thumb).into(recipeImageView)

        recipeCardView.setOnClickListener {// when recipe selected send it to RecipeListActivity
           itemSelected?.invoke(recipe)
        }

        return rowView
    }

    override fun getItemViewType(position: Int): Int {
            return position
        }
}

