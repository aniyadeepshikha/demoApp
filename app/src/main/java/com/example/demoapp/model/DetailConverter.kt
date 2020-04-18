package com.example.demoapp.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DetailConverter{
    @TypeConverter
    fun fromDetailsList(detailsList: ArrayList<Details?>?): String? {
        if (detailsList == null) {
            return null
        }
        val type: Type = object : TypeToken<ArrayList<Details?>?>() {}.getType()
        return Gson().toJson(detailsList, type)
    }

    @TypeConverter
    fun toDetailsList(detailsString: String?): ArrayList<Details?>? {
        if (detailsString == null) {
            return null
        }
        val type: Type = object : TypeToken<ArrayList<Details?>?>() {}.getType()
        return Gson().fromJson(detailsString, type)
    }
}