/**
 * This is the common structure for all responses
 * if the response contains a list(array) then it will have 'items' field
 * if the response contains a single item then it will have 'item'  field
 */


package com.mpakbaz.mycv.data.response


import com.google.gson.annotations.SerializedName

import java.io.Serializable

class ApiSingleResponse<T> : ApiResponse(), Serializable {

    @SerializedName("result")
    var result: T? = null
}
