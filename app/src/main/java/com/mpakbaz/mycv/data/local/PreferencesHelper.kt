package com.mpakbaz.mycv.data.local

import com.mpakbaz.mycv.injection.ApplicationContext
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PreferencesHelper @Inject
constructor(@ApplicationContext context: Context) {

    private val mPref: SharedPreferences

    init {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun deleteAllPrefernces() {
        mPref.edit().clear().apply()
    }

    fun saveToken(value: String) {
        mPref.edit().putString(JWT_TOKEN_KEY, value).apply()
    }

    fun readToken(): String {
        return mPref.getString(JWT_TOKEN_KEY, "") ?: ""
    }

    companion object {
        const val PREF_FILE_NAME = "my_cv_pref_file"
        const val JWT_TOKEN_KEY = "JWT_TOKEN_KEY"
    }

}
