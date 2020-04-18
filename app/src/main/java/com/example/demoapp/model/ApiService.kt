package com.example.demoapp.model

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("facts")
    fun getData() : Call<CountryDetails>
}