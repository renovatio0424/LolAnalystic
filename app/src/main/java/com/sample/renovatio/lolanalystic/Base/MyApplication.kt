package com.sample.renovatio.lolanalystic.Base

import android.app.Application
import com.sample.renovatio.lolanalystic.Di.myDiModule
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext, myDiModule)
    }
}