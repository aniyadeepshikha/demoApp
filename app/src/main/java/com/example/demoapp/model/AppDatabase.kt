package com.example.demoapp.model

import android.content.Context
import androidx.room.*

@Database(entities = arrayOf(CountryDetails::class, Details::class), version = 1)
@TypeConverters(DetailConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun detailsDao() : CountryDetailsDao

    private var instance: AppDatabase? = null
    fun getInstance(context : Context) : AppDatabase?{
        if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_database")
                    .fallbackToDestructiveMigration()
                    .build()
        }
        return instance
    }

}