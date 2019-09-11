package com.mpakbaz.mycv.features.login

import android.annotation.SuppressLint
import com.mpakbaz.mycv.data.DataManager
import com.mpakbaz.mycv.data.local.PreferencesHelper
import com.mpakbaz.mycv.data.model.LoginResponse
import com.mpakbaz.mycv.data.response.ApiSingleResponse
import com.mpakbaz.mycv.injection.ConfigPersistent
import com.mpakbaz.mycv.features.base.BasePresenter
import com.mpakbaz.mycv.util.rx.scheduler.SchedulerUtils
import javax.inject.Inject

@ConfigPersistent
class LoginPresenter @Inject
constructor(private val mDataManager: DataManager, private val mPreferencesHelper: PreferencesHelper) : BasePresenter<LoginMvpView>() {

    @SuppressLint("CheckResult")
    fun login(email: String, password: String) {
        checkViewAttached()
        mvpView?.showProgress(true)
        mDataManager.login(email, password)
                .compose(SchedulerUtils.ioToMain<ApiSingleResponse<LoginResponse>>())
                .subscribe({ data ->
                    mvpView?.showProgress(false)
                    data.result?.token?.let { mPreferencesHelper.saveToken(it) }
                    mvpView?.loginSuccess(data.result)
                }) { throwable ->
                    mvpView?.showProgress(false)
                    mvpView?.showError(throwable)
                }
    }

    fun checkIsLoggedIn(): Boolean {
        return mPreferencesHelper.readToken().isNotEmpty()
    }

}