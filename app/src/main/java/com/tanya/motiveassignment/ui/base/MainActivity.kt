package com.tanya.motiveassignment.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tanya.motiveassignment.R
import com.tanya.motiveassignment.data.api.NetworkHelper
import com.tanya.motiveassignment.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment()
        if(!NetworkHelper.isOnline(applicationContext)) {
            Toast.makeText(this, "You are currently offline", Toast.LENGTH_LONG).show()
        }
    }

    private fun addFragment() {
        if(supportFragmentManager.findFragmentByTag(SearchFragment.TAG) == null){
            supportFragmentManager.beginTransaction().add(R.id.fragment_container,
                SearchFragment.newInstance(), SearchFragment.TAG).commit()
        }
    }
}