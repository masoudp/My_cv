package com.mpakbaz.mycv

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import com.mpakbaz.mycv.injection.component.ApplicationComponent
import com.mpakbaz.mycv.injection.component.DaggerApplicationComponent
import com.mpakbaz.mycv.injection.module.ApplicationModule
import timber.log.Timber

class App : MultiDexApplication() {

    private var mApplicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
            LeakCanary.install(this)
        }
    }

    var component: ApplicationComponent
        get() {
            if (mApplicationComponent == null) {
                mApplicationComponent = DaggerApplicationComponent.builder()
                        .applicationModule(ApplicationModule(this))
                        .build()
            }
            return mApplicationComponent as ApplicationComponent
        }
        set(applicationComponent) {
            mApplicationComponent = applicationComponent
        }

    companion object {

        operator fun get(context: Context): App {
            return context.applicationContext as App
        }
    }
}
