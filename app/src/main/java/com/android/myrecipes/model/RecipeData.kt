package com.android.myrecipes.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class RecipeData (
    val calories : String?,
    val carbos : String?,
    val description : String?,
    val difficulty : Int,
    val fats : String?,
    val headline : String?,
    val id : String?,
    val image : String?,
    val name : String?,
    val proteins : String?,
    val thumb : String?,
    val time : String?
) : Serializable


