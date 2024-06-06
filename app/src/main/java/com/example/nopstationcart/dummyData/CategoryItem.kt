package com.example.nopstationcart.dummyData

import android.os.Parcel
import android.os.Parcelable

data class CategoryItem(val imageResId: Int, val text:String): Parcelable {constructor(parcel:Parcel):this(parcel.readInt(),parcel.readString()?:"")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(imageResId)
        parcel.writeString(text)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CategoryItem> {
        override fun createFromParcel(parcel: Parcel): CategoryItem {
            return CategoryItem(parcel)
        }

        override fun newArray(size: Int): Array<CategoryItem?> {
            return arrayOfNulls(size)
        }
    }
}
