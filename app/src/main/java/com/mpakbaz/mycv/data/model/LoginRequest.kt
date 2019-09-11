package com.mpakbaz.mycv.data.model

import androidx.annotation.Keep

@Keep
data class LoginRequest(val username: String, val password: String)
