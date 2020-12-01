package com.oussama.domain

import android.app.Application
import java.lang.ref.WeakReference

object DomainIntegration {

    lateinit var applicationReference: WeakReference<Application>

    fun init(application: Application){
        applicationReference = WeakReference(application)
    }

    fun getApplication(): Application = applicationReference.get()!!
}