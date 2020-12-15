package com.oussama.stackoverflow_app_example.app.features.core

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

val scheduler : SchedulerProvider by lazy { SchedulerProviderImplementation() }

interface SchedulerProvider {
    fun uiScheduler(): Scheduler
    fun ioScheduler(): Scheduler
}

class SchedulerProviderImplementation : SchedulerProvider{
    override fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()

    override fun ioScheduler(): Scheduler = Schedulers.io()
}