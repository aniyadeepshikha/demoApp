package com.example.demoapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapp.R
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
