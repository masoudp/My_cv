package com.mpakbaz.mycv.injection.component

import com.mpakbaz.mycv.injection.PerActivity
import com.mpakbaz.mycv.injection.module.ActivityModule
import com.mpakbaz.mycv.features.base.BaseActivity
import com.mpakbaz.mycv.features.detail.DetailActivity
import com.mpakbaz.mycv.features.login.LoginActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(baseActivity: BaseActivity)

    fun inject(loginActivity: LoginActivity)

    fun inject(detailActivity: DetailActivity)
}
