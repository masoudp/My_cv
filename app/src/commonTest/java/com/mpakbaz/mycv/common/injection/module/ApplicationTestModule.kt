package com.mpakbaz.mycv.common.injection.module

import com.mpakbaz.mycv.data.DataManager
import com.mpakbaz.mycv.data.remote.MyCVService
import com.mpakbaz.mycv.injection.ApplicationContext
import android.app.Application
import android.content.Context
import com.mpakbaz.mycv.common.TestDataFactory
import com.mpakbaz.mycv.data.local.PreferencesHelper
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import javax.inject.Singleton

/**
 * Provides application-level dependencies for an app running on a testing environment
 * This allows injecting mocks if necessary.
 */
@Module
class ApplicationTestModule(private val mApplication: Application) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return mApplication
    }

    /*************
     * MOCKS
     */

    @Provides
    @Singleton
    internal fun providesDataManager(): DataManager {
        val dataManager=DataManager(provideMvpBoilerplateService())
        given(dataManager.login(TestDataFactory.makeTestUserName(),TestDataFactory.makeTestPassword()))
                .willReturn(Single.just(TestDataFactory.makeTestLoginResponse()))

        given(dataManager.getMyCv())
                .willReturn(Single.just(TestDataFactory.makeTestGetCVResponse()))

        return dataManager
    }

    @Provides
    @Singleton
    internal fun providesPreferencesHelper(): PreferencesHelper {
        return PreferencesHelper(provideContext())
    }

    @Provides
    @Singleton
    internal fun provideMvpBoilerplateService(): MyCVService {
        return mock(MyCVService::class.java)
    }

}
