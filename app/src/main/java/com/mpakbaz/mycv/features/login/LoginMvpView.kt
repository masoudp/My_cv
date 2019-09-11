package com.mpakbaz.mycv.features.login

import com.mpakbaz.mycv.data.model.LoginResponse
import com.mpakbaz.mycv.features.base.MvpView

interface LoginMvpView : MvpView {

    fun loginSuccess(data: LoginResponse?)

    fun showProgress(show: Boolean)

    fun showError(error: Throwable)

}