package com.example.demoapp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface CountryDetailsDao {

        @Insert
        fun insertDetail(detail : CountryDetails) : Long

        @Query("SELECT * from CountryDetails")
        fun getFacts(): LiveData<CountryDetails?>?

        @Query("DELETE FROM CountryDetails")
        fun deleteAll()
}