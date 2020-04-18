package com.example.demoapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Details(@PrimaryKey(autoGenerate = true) val id : Int, var title : String, var description : String, var imageHref : String)