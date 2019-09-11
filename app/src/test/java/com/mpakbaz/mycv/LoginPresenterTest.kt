package com.mpakbaz.mycv

import com.mpakbaz.mycv.common.TestDataFactory
import com.mpakbaz.mycv.data.DataManager
import com.mpakbaz.mycv.data.local.PreferencesHelper
import com.mpakbaz.mycv.data.model.LoginResponse
import com.mpakbaz.mycv.data.response.ApiSingleResponse
import com.mpakbaz.mycv.features.login.LoginMvpView
import com.mpakbaz.mycv.features.login.LoginPresenter
import com.mpakbaz.mycv.util.RxSchedulersOverrideRule
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginPresenterTest {

    @Mock
    lateinit var mMockLoginMvpView: LoginMvpView
    @Mock
    lateinit var mMockDataManager: DataManager
    @Mock
    lateinit var mMockPreferencesHelper: PreferencesHelper

    private var mLoginPresenter: LoginPresenter? = null

    @JvmField
    @Rule
    val mOverrideSchedulersRule = RxSchedulersOverrideRule()

    @Before
    fun setUp() {
        mLoginPresenter = LoginPresenter(mMockDataManager, mMockPreferencesHelper)
        mLoginPresenter?.attachView(mMockLoginMvpView)
    }

    @After
    fun tearDown() {
        mLoginPresenter?.detachView()
    }

    @Test
    @Throws(Exception::class)
    fun getLoginReturnLoginResponse() {
        val loginResponse = TestDataFactory.makeTestLoginResponse()
        val userName = TestDataFactory.makeTestUserName()
        val password = TestDataFactory.makeTestPassword()

        `when`(mMockDataManager.login(userName, password))
                .thenReturn(Single.just(loginResponse))

        mLoginPresenter?.login(userName, password)

        verify<LoginMvpView>(mMockLoginMvpView, times(2)).showProgress(anyBoolean())
        verify<LoginMvpView>(mMockLoginMvpView).loginSuccess(loginResponse.result)
        verify<LoginMvpView>(mMockLoginMvpView, never()).showError(RuntimeException())

    }

    @Test
    @Throws(Exception::class)
    fun getLoginReturnsError() {
        val userName = TestDataFactory.makeTestUserName()
        val password = TestDataFactory.makeTestPassword()
        val runtimeException = RuntimeException()
        `when`(mMockDataManager.login(userName,password))
                .thenReturn(Single.error<ApiSingleResponse<LoginResponse>>(runtimeException))

        mLoginPresenter?.login(userName,password)

        verify<LoginMvpView>(mMockLoginMvpView, times(2)).showProgress(anyBoolean())
        verify<LoginMvpView>(mMockLoginMvpView).showError(runtimeException)
        verify<LoginMvpView>(mMockLoginMvpView, never()).loginSuccess(ArgumentMatchers.any<LoginResponse>())
    }

}