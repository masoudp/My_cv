package com.mpakbaz.mycv.features.login

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.google.android.material.snackbar.Snackbar
import com.mpakbaz.mycv.R
import com.mpakbaz.mycv.data.model.LoginResponse
import com.mpakbaz.mycv.features.base.BaseActivity
import com.mpakbaz.mycv.features.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject


class LoginActivity : BaseActivity(), LoginMvpView {

    @Inject
    lateinit var mLoginPresenter: LoginPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        mLoginPresenter.attachView(this)

        if (mLoginPresenter.checkIsLoggedIn()) {
            openDetailsActivity()
        }

        btn_login.setOnClickListener {
            onLogin()
        }
    }

    private fun openDetailsActivity() {
        startActivity(DetailActivity.getStartIntent(this))
        finish()
    }

    private fun onLogin() {
        tv_email.error = null
        tv_pass.error = null

        if (tv_email.text.isNullOrEmpty()) {
            tv_email.error = getString(R.string.error_please_enter_email)
            return
        }

        if (tv_pass.text.isNullOrEmpty()) {
            tv_pass.error = getString(R.string.error_please_enter_password)
            return
        }

        mLoginPresenter.login(tv_email.text.toString(), tv_pass.text.toString())
    }

    override val layout: Int
        get() = R.layout.activity_login

    override fun onDestroy() {
        super.onDestroy()
        mLoginPresenter.detachView()
    }

    override fun loginSuccess(data: LoginResponse?) {
        openDetailsActivity()
    }

    override fun showProgress(show: Boolean) {
        const_loading?.visibility = if (show) VISIBLE else GONE
        const_login?.visibility = if (show) GONE else VISIBLE
    }

    override fun showError(error: Throwable) {
        Snackbar.make(const_login, R.string.error_invalid_credentials, Snackbar.LENGTH_LONG)
                .setActionTextColor(resources.getColor(android.R.color.holo_red_light))
                .show()
    }
}