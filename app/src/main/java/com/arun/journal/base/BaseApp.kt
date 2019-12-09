package com.arun.journal.base

import android.app.Application
import com.arun.journal.network.ApiFactory
import com.arun.journal.util.InternetUtil
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Base class for application, needed to initialize Fresco
 */
class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        InternetUtil.init(this)
        ApiFactory.init(this)
    }
}