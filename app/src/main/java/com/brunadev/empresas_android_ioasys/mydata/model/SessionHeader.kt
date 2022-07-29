package com.brunadev.empresas_android_ioasys.mydata.model

import com.google.gson.annotations.SerializedName

data class SessionHeader(
    @SerializedName("uid") val uid: String?,
    @SerializedName("access-token") val accessToken: String?,
    @SerializedName("client") val client: String?
)