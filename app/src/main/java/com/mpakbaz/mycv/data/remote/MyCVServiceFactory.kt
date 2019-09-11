package com.mpakbaz.mycv.data.remote

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mpakbaz.mycv.BuildConfig
import com.mpakbaz.mycv.data.local.PreferencesHelper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Provide "make" methods to create instances of [MyCVService]
 * and its related dependencies, such as OkHttpClient, Gson, etc.
 */
object MyCVServiceFactory {

    fun makeStarterService(preferencesHelper: PreferencesHelper): MyCVService {
        return makeService(preferencesHelper, makeGson())
    }

    private fun makeService(preferencesHelper: PreferencesHelper, gson: Gson): MyCVService {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(makeOkHttpClient(preferencesHelper))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(MyCVService::class.java)
    }

    private fun makeOkHttpClient(preferencesHelper: PreferencesHelper): OkHttpClient {

        val httpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = MainHttpInterceptor(preferencesHelper)
            httpClientBuilder.addInterceptor(loggingInterceptor)
            httpClientBuilder.addNetworkInterceptor(StethoInterceptor())
        }

        return httpClientBuilder.build()
    }

    private fun makeGson(): Gson {
        return GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }
}
