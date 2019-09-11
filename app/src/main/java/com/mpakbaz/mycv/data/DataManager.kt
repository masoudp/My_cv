package com.mpakbaz.mycv.data

import com.mpakbaz.mycv.data.model.CVData
import com.mpakbaz.mycv.data.model.LoginResponse
import com.mpakbaz.mycv.data.model.LoginRequest
import com.mpakbaz.mycv.data.remote.MyCVService
import com.mpakbaz.mycv.data.response.ApiSingleResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject
constructor(private val mMyCVService: MyCVService) {

    fun login(email: String, password: String): Single<ApiSingleResponse<LoginResponse>> {
        return mMyCVService.login(LoginRequest(email, password))
    }

    fun getMyCv(): Single<ApiSingleResponse<CVData>> {
        return mMyCVService.getMyCv()
    }

}