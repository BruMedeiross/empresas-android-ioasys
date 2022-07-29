package com.brunadev.empresas_android_ioasys.mydata.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Company(
    @SerializedName("enterprise_name") val name: String?,
    @SerializedName("enterprise_type") val type: CompanyType,
    @SerializedName("description") val description: String?,
    @SerializedName("country") val country: String?,
    @SerializedName("photo") val photo: String? = ""
): Parcelable