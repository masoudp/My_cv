package com.mpakbaz.mycv.injection.component

import com.mpakbaz.mycv.data.DataManager
import com.mpakbaz.mycv.data.remote.MyCVService
import com.mpakbaz.mycv.injection.ApplicationContext
import com.mpakbaz.mycv.injection.module.ApplicationModule
import android.app.Application
import android.content.Context
import com.mpakbaz.mycv.data.local.PreferencesHelper
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun dataManager(): DataManager

    fun preferencesHelper(): PreferencesHelper

    fun myCVService(): MyCVService
}
