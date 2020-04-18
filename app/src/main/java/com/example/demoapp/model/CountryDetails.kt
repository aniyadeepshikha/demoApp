package com.example.demoapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CountryDetails(@PrimaryKey(autoGenerate = true) val id : Int, var title : String, var rows : ArrayList<Details>)