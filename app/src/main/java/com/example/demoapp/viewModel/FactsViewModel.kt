package com.example.demoapp.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.demoapp.model.CountryDetails
import com.example.demoapp.model.DbRepository
import com.example.demoapp.model.WebServiceRepository


class FactsViewModel : ViewModel() {

    private var liveData: LiveData<CountryDetails?>? = null
    private var webRepository: WebServiceRepository? = null
    private var context: Context? = null

    fun init(context: Context) {
        this.context = context
        if (liveData != null) {
            return
        }
        WebServiceRepository.getInstance()?.getFacts(context)
        liveData = DbRepository.getInstance(context)!!.getFacts()
    }

    fun getFact(): LiveData<CountryDetails?>? {
        return liveData
    }

}