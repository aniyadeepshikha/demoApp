package com.example.demoapp.view

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentTransaction
import com.example.demoapp.R
import com.example.demoapp.model.WebServiceRepository

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        loadFragment()
    }

    private fun loadFragment() {
        supportFragmentManager.beginTransaction().add(R.id.container, FactsListFragment()).commit()

    }
}
