package com.brunadev.empresas_android_ioasys.common.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email") val email: String?,
    @SerializedName("password") val password: String?
)