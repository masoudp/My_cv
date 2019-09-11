package com.mpakbaz.mycv.data.remote


import android.content.Context
import android.net.ConnectivityManager
import com.mpakbaz.mycv.BuildConfig
import com.mpakbaz.mycv.data.local.PreferencesHelper
import com.mpakbaz.mycv.util.JsonUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import timber.log.Timber
import java.io.IOException
import java.nio.charset.Charset
import java.util.*

class MainHttpInterceptor(private val preferencesHelper: PreferencesHelper) : Interceptor {


    private fun hasNetwork(context: Context): Boolean {
        var isConnected = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()


        val headerBuilder = original.newBuilder()
                .header("User-Agent", "SaveApp-Android")
                .header("Accept", "application/json")


        val token = preferencesHelper.readToken()
        if (token.isNotEmpty()) {
            headerBuilder.header("Authorization", "Bearer $token")
        }
        val request = headerBuilder
                .method(original.method(), original.body())
                .build()

        if (BuildConfig.DEBUG) {
            //            String requestLog = String.format("Sending request %s on %s%n \nUser Info: %s", request.url(), chain.connection(), "null");
            val requestBodyString = bodyToString(request)
            val requestStr = StringBuilder()
                    .append("\nURL:\n").append(request.url()).append("\n")
                    .append("Headers:\n")
                    .append(request.headers()).append("\n")
                    .append("Request Body:\n")
                    .append(requestBodyString).append("\n")
                    .toString()
            Timber.tag(TAG).i(requestStr)
        }

        val t1 = System.currentTimeMillis()
        val response = chain.proceed(request)
        val t2 = System.currentTimeMillis()

        if (BuildConfig.DEBUG) {
            val responseLog = String.format(Locale.ENGLISH, "Received response for %s in %d ms",
                    response.request().url(), t2 - t1)

            var bodyString = ""
            try {
                val responseBody = response.body()
                val source = responseBody?.source()
                source?.request(java.lang.Long.MAX_VALUE) // request the entire body.
                val buffer = source?.buffer()
                // clone buffer before reading from it
                bodyString = buffer?.clone()?.readString(Charset.forName("UTF-8")) ?: ""
            } catch (e: Exception) {
                e.printStackTrace()
            }

            val prettyJson = JsonUtils.prettyJson(bodyString)

            Timber.tag(TAG).i("RESPONSE:\n$responseLog")
            Timber.tag(TAG).i("Response Body:%s\n", prettyJson)
        }

        return response
    }

    companion object {

        const val TAG = "interceptor"
    }

    private fun bodyToString(request: Request): String {
        return try {
            val copy = request.newBuilder().build()
            val buffer = Buffer()
            if (copy.body() != null) {
                copy.body()?.writeTo(buffer)
                JsonUtils.prettyJson(buffer.readUtf8()).toString()
            } else {
                ""
            }
        } catch (e: IOException) {
            "did not work"
        }
    }

}
