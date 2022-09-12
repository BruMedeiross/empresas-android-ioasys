package com.brunadev.empresas_android_ioasys.common.model

import com.google.gson.annotations.SerializedName

class CompanyList (

    @SerializedName("enterprises") val enterprises: List<Company> = emptyList()

) {

}