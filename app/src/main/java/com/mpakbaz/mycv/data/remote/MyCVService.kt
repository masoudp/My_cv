package com.mpakbaz.mycv.data.remote


import com.mpakbaz.mycv.data.model.*
import com.mpakbaz.mycv.data.response.ApiSingleResponse
import io.reactivex.Single
import retrofit2.http.*

interface MyCVService {

    @POST("api/auth")
    fun login(@Body data: LoginRequest): Single<ApiSingleResponse<LoginResponse>>

    @GET("api/cvData/getMyCv")
    fun getMyCv(): Single<ApiSingleResponse<CVData>>

}
