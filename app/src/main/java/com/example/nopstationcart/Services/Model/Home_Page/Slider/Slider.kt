package com.example.nopstationcart.Services.Model.Home_Page.Slider

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Banner")
data class Slider(
    val EntityId: Int,
    @PrimaryKey
    val Id: Int,
    val ImageUrl: String,
    val SliderType: Int
)