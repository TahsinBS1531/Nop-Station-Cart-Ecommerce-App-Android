package com.example.nopstationcart.model.data

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.android.parcel.Parcelize

@Parcelize
data class categoryDetailsItem(var tittle:String,var imageResID:Int):Parcelable

