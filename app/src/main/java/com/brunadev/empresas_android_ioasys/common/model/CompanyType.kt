package com.brunadev.empresas_android_ioasys.common.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CompanyType (
    @SerializedName("id") val id: Int,
    @SerializedName("enterprise_type_name") val enterpriseTypeName: String
    ) : Parcelable
