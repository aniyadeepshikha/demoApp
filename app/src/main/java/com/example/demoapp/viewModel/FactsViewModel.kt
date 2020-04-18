package com.example.demoapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demoapp.model.CountryDetails
import com.example.demoapp.model.WebServiceRepository


class FactsViewModel : ViewModel() {

    private var mutableLiveData: MutableLiveData<CountryDetails?>? = null
    private var webRepository: WebServiceRepository? = null

    fun init() {
        if (mutableLiveData != null) {
            return
        }
        webRepository = WebServiceRepository.getInstance()
        mutableLiveData = webRepository?.getFacts()
    }

    fun getFact(): LiveData<CountryDetails?>? {
        return mutableLiveData
    }

}