package com.android.myrecipes.model

import android.os.Parcel
import android.os.Parcelable

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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(calories)
        parcel.writeString(carbos)
        parcel.writeString(description)
        parcel.writeInt(difficulty)
        parcel.writeString(fats)
        parcel.writeString(headline)
        parcel.writeString(id)
        parcel.writeString(image)
        parcel.writeString(name)
        parcel.writeString(proteins)
        parcel.writeString(thumb)
        parcel.writeString(time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecipeData> {
        override fun createFromParcel(parcel: Parcel): RecipeData {
            return RecipeData(parcel)
        }

        override fun newArray(size: Int): Array<RecipeData?> {
            return arrayOfNulls(size)
        }
    }
}