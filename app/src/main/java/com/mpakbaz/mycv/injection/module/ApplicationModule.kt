package com.mpakbaz.mycv.injection.module

import android.app.Application
import android.content.Context
import com.mpakbaz.mycv.data.local.PreferencesHelper

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import com.mpakbaz.mycv.data.remote.MyCVService
import com.mpakbaz.mycv.data.remote.MyCVServiceFactory
import com.mpakbaz.mycv.injection.ApplicationContext

@Module
class ApplicationModule(private val mApplication: Application) {

    @Provides
    internal fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return mApplication
    }

    @Provides
    @Singleton
    internal fun provideMvpStarterService(): MyCVService {
        return MyCVServiceFactory.makeStarterService(PreferencesHelper(mApplication))
    }
}
