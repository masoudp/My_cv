package com.mpakbaz.mycv.injection.component

import com.mpakbaz.mycv.injection.ConfigPersistent
import com.mpakbaz.mycv.injection.module.ActivityModule
import com.mpakbaz.mycv.injection.module.FragmentModule
import com.mpakbaz.mycv.features.base.BaseActivity
import com.mpakbaz.mycv.features.base.BaseFragment
import dagger.Component

/**
 * A dagger component that will live during the lifecycle of an Activity or Fragment but it won't
 * be destroy during configuration changes. Check [BaseActivity] and [BaseFragment] to
 * see how this components survives configuration changes.
 * Use the [ConfigPersistent] scope to annotate dependencies that need to survive
 * configuration changes (for example Presenters).
 */
@ConfigPersistent
@Component(dependencies = [ApplicationComponent::class])
interface ConfigPersistentComponent {

    fun activityComponent(activityModule: ActivityModule): ActivityComponent

    fun fragmentComponent(fragmentModule: FragmentModule): FragmentComponent

}
