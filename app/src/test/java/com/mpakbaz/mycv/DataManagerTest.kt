package com.mpakbaz.mycv

import com.mpakbaz.mycv.common.TestDataFactory
import com.mpakbaz.mycv.data.DataManager
import com.mpakbaz.mycv.data.model.CVData
import com.mpakbaz.mycv.data.model.LoginResponse
import com.mpakbaz.mycv.data.remote.MyCVService
import com.mpakbaz.mycv.data.response.ApiSingleResponse
import com.mpakbaz.mycv.util.RxSchedulersOverrideRule
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataManagerTest {

    @Rule @JvmField val mOverrideSchedulersRule = RxSchedulersOverrideRule()
    @Mock lateinit var mMockMyCVService: MyCVService

    private var mDataManager: DataManager? = null

    @Before
    fun setUp() {
        mDataManager = DataManager(mMockMyCVService)
    }

    @Test
    fun getLoginData() {
        val userName = TestDataFactory.makeTestUserName()
        val password = TestDataFactory.makeTestPassword()
        val response = ApiSingleResponse<LoginResponse>()

        val loginData = TestDataFactory.makeTestLoginData()



        `when`(mMockMyCVService.login(loginData))
                .thenReturn(Single.just(response))

        mDataManager?.login(userName,password)
                ?.test()
                ?.assertComplete()
    }

    @Test
    fun getCVData() {
        val cvData = TestDataFactory.makeCvData()
        val respn = ApiSingleResponse<CVData>()
        respn.result =cvData
        `when`(mMockMyCVService.getMyCv())
                .thenReturn(Single.just(respn))

        mDataManager?.getMyCv()
                ?.test()
                ?.assertComplete()
                ?.assertValue(respn)
    }
}
