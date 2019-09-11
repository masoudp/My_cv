/**
 * This is the common structure for all responses
 * if the response contains a list(array) then it will have 'items' field
 * if the response contains a single item then it will have 'item'  field
 */


package com.mpakbaz.mycv.data.response


import com.google.gson.annotations.SerializedName

import java.io.Serializable

open class ApiResponse : Serializable {


    @SerializedName("success")
    private val success: Boolean = false
    @SerializedName("currentTime")
    private val currentTime: Long = 0
    @SerializedName("errorMessage")
    private val errorMessage: String? = null
    @SerializedName("newToken")
    private val newToken: String? = null
}
