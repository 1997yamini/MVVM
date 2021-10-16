package com.app.demomvvm

import android.app.Application
import com.app.demomvvm.di.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

import org.koin.core.module.Module

class DemoMVVMApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(this@DemoMVVMApplication)
            val modules = ArrayList<Module>()
            modules.add(viewModels)
            modules(modules)
        }
    }

    companion object {
        var isAppLive = false
        private var sActiveActivities = 0

        fun activityStarted() {
            if (sActiveActivities == 0) {
                isAppLive = true
            }
            sActiveActivities++
        }

        fun activityStopped() {
            sActiveActivities--
            if (sActiveActivities == 0) {
                isAppLive = false
            }
        }

        lateinit var instance: DemoMVVMApplication
    }
}