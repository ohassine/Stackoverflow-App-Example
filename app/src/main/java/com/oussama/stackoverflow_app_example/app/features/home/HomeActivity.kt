package com.oussama.stackoverflow_app_example.app.features.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.oussama.stackoverflow_app_example.R

class HomeActivity : AppCompatActivity(R.layout.activity_main) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager
            .takeIf { savedInstanceState == null }
            ?.beginTransaction()
            ?.replace(R.id.container, UsersFragment())
            ?.commit()

    }

    fun addDetailsFragmentWithTransition(fragment: Fragment) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack("")

        transaction.commit()
    }
}