package com.example.demoapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = arrayOf(CountryDetails::class), version = 1)
@TypeConverters(DetailConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun detailsDao(): CountryDetailsDao

    companion object {

        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "demo_app_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }

}