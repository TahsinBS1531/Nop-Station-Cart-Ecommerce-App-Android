package com.example.nopstationcart.Services.Local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nopstationcart.Services.Model.Home_Page.Slider.Slider

@Dao
interface BannerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBannerList(bannerList: List<Slider>)

    @Query("SELECT * FROM banner")
    suspend fun getBannerList(): List<Slider>

}