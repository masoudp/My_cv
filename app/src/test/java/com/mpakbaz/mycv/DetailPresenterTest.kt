package com.mpakbaz.mycv

import com.mpakbaz.mycv.common.TestDataFactory
import com.mpakbaz.mycv.data.DataManager
import com.mpakbaz.mycv.data.model.CVData
import com.mpakbaz.mycv.data.response.ApiSingleResponse
import com.mpakbaz.mycv.features.detail.DetailMvpView
import com.mpakbaz.mycv.features.detail.DetailPresenter
import com.mpakbaz.mycv.util.RxSchedulersOverrideRule
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DetailPresenterTest {

    @Mock
    lateinit var mMockDetailMvpView: DetailMvpView
    @Mock
    lateinit var mMockDataManager: DataManager
    private var mDetailPresenter: DetailPresenter? = null

    @JvmField
    @Rule
    val mOverrideSchedulersRule = RxSchedulersOverrideRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mDetailPresenter = DetailPresenter(mMockDataManager)
        mDetailPresenter?.attachView(mMockDetailMvpView)
    }

    @After
    fun tearDown() {
        mDetailPresenter?.detachView()
    }

    @Test
    @Throws(Exception::class)
    fun getCVDetailReturnsCV() {
        val getCVResponse = TestDataFactory.makeTestGetCVResponse()

        `when`(mMockDataManager.getMyCv())
                .thenReturn(Single.just(getCVResponse))

        mDetailPresenter?.getMyCv()

        verify<DetailMvpView>(mMockDetailMvpView, times(2)).showProgress(anyBoolean())
        verify<DetailMvpView>(mMockDetailMvpView).showCVData(getCVResponse.result)
        verify<DetailMvpView>(mMockDetailMvpView, never()).showError(RuntimeException())
    }

    @Test
    @Throws(Exception::class)
    fun getCVDetailReturnsError() {
        val runtimeException = RuntimeException()
        `when`(mMockDataManager.getMyCv())
                .thenReturn(Single.error<ApiSingleResponse<CVData>>(runtimeException))

        mDetailPresenter?.getMyCv()

        verify<DetailMvpView>(mMockDetailMvpView, times(2)).showProgress(anyBoolean())
        verify<DetailMvpView>(mMockDetailMvpView).showError(runtimeException)
    }

}