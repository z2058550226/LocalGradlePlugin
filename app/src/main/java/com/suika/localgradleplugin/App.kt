package com.suika.localgradleplugin

import android.app.Application
import com.suika.astree.AndroidStudioTree
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(AndroidStudioTree())
    }
}