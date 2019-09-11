package com.mpakbaz.mycv.common.injection.component

import com.mpakbaz.mycv.common.injection.module.ApplicationTestModule
import com.mpakbaz.mycv.injection.component.ApplicationComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationTestModule::class])
interface TestComponent : ApplicationComponent