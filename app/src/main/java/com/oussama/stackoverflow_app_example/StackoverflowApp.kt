package com.oussama.stackoverflow_app_example

import android.app.Application
import com.oussama.domain.DomainIntegration

class StackoverflowApp : Application() {

    override fun onCreate() {
        super.onCreate()
        DomainIntegration.init(this)
    }

}