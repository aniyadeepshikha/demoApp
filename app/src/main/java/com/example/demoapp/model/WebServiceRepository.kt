package com.example.demoapp.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class WebServiceRepository {


    var apiService: ApiService? = null

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    companion object {
        private var webRepository: WebServiceRepository? = null
        fun getInstance(): WebServiceRepository? {
        if (webRepository == null) {
            webRepository = WebServiceRepository()
        }
        return webRepository
    }
}

        public fun getFacts(): MutableLiveData<CountryDetails?>? {
            val facts: MutableLiveData<CountryDetails?> = MutableLiveData<CountryDetails?>()
            apiService?.getData()?.enqueue(object : Callback<CountryDetails?> {
                override fun onResponse(
                    call: Call<CountryDetails?>?,
                    response: Response<CountryDetails?>
                ) {
                    if (response.isSuccessful()) {
                        facts.setValue(response.body())
                        Log.d("WebService Call--", "deep---"+response.body())
                    }
                }

                override fun onFailure(call: Call<CountryDetails?>?, t: Throwable?) {
                    Log.d("WebService Call--", "error---")

                    facts.setValue(null)
                }
            })
            return facts
        }
}