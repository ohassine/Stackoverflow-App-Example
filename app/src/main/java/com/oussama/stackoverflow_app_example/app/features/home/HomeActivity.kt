package com.oussama.stackoverflow_app_example.app.features.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.oussama.stackoverflow_app_example.R

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .takeIf { savedInstanceState == null }
            ?.beginTransaction()
            ?.replace(R.id.container, UsersFragment())
            ?.commit()

    }

}