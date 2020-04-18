package com.example.demoapp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CountryDetailsDao {

        @Insert
        fun insertDetail(detail : CountryDetails) : Long

        @Query("select * from CountryDetails")
        fun getCountryDetails() :  CountryDetails

}