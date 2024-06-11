package com.example.nopstationcart.Services.Local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.nopstationcart.Services.Local.Converters.Converter
import com.example.nopstationcart.Services.Model.CategoryList.Data
import com.example.nopstationcart.Services.Model.Home_Page.Slider.Slider

@Database(entities = [FeaturedProductsEntity::class,Slider::class,Data::class,OrderDetailsEntity::class], version = 6, exportSchema = false)
@TypeConverters(Converter::class)

abstract class AppDatabase:RoomDatabase() {
    abstract fun featuredProductDao(): FeaturedProductsDao
    abstract fun bannerDao():BannerDao

    abstract fun categoryDao():CategoryDao

    abstract fun orderDetailsDao():OrderDetailsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}