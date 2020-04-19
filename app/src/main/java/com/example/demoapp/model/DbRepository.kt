package com.example.demoapp.model

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class DbRepository {

    companion object {
        private var dbRepository: DbRepository? = null
        private var countryDetailsDao: CountryDetailsDao? = null
        var details: LiveData<CountryDetails?>? = null

        fun getInstance(context: Context?): DbRepository? {
            if (dbRepository == null) {
                dbRepository = DbRepository()

                val db: AppDatabase? = AppDatabase.getInstance(context!!)
                countryDetailsDao = db!!.detailsDao()
                details = countryDetailsDao!!.getFacts()
            }
            return dbRepository
        }
    }

    fun getFacts(): LiveData<CountryDetails?>? {
        return details
    }

    fun insertDetails(details: CountryDetails?) {
        InsertAsyncTask(countryDetailsDao!!).execute(details)
    }

    fun deleteAll() {
        DeleteAsyncTask(countryDetailsDao!!).execute()
    }

    private class InsertAsyncTask internal constructor(dao: CountryDetailsDao) :
        AsyncTask<CountryDetails?, Void?, Void?>() {
        private val mAsyncTaskDao: CountryDetailsDao

        init {
            mAsyncTaskDao = dao
        }

        override fun doInBackground(vararg params: CountryDetails?): Void? {
            mAsyncTaskDao.insertDetail(params[0]!!)
            return null
        }
    }

    private class DeleteAsyncTask internal constructor(dao: CountryDetailsDao) :
        AsyncTask<Void?, Void?, Void?>() {
        private val mAsyncTaskDao: CountryDetailsDao

        init {
            mAsyncTaskDao = dao
        }

        override fun doInBackground(vararg params: Void?): Void? {
            mAsyncTaskDao.deleteAll()
            return null
        }
    }
}