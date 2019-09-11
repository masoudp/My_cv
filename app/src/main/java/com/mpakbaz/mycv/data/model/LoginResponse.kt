package com.mpakbaz.mycv.data.model

import androidx.annotation.Keep

@Keep
data class LoginResponse(val id: Long, val token: String, val fullName: String, val avatar: String)
