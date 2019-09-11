package com.mpakbaz.mycv.injection.module

import android.app.Activity
import android.content.Context

import dagger.Module
import dagger.Provides
import com.mpakbaz.mycv.injection.ActivityContext

@Module
class ActivityModule(private val mActivity: Activity) {

    @Provides
    internal fun provideActivity(): Activity {
        return mActivity
    }

    @Provides
    @ActivityContext
    internal fun providesContext(): Context {
        return mActivity
    }
}
