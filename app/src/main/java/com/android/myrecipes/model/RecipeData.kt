package com.android.myrecipes.model

import java.io.Serializable

data class RecipeData (
    var calories : String?,
    val carbos : String?,
    val description : String?,
    val difficulty : Int,
    var fats : String?,
    val headline : String?,
    val id : String?,
    val image : String?,
    val name : String?,
    var proteins : String?,
    val thumb : String?,
    val time : String?
) : Serializable


