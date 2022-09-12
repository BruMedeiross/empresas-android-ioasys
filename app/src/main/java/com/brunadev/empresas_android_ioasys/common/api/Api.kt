package com.brunadev.empresas_android_ioasys.common.api

import com.brunadev.empresas_android_ioasys.common.model.CompanyList
import com.brunadev.empresas_android_ioasys.login.data.LoginResponse
import retrofit2.Call
import retrofit2.http.*


interface Api {

    @FormUrlEncoded
    @POST("users/auth/sign_in")
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

   @GET("enterprises")
    fun findCompanies(
       @Header("uid") uid: String?,
       @Header("client") client: String?,
       @Header("access-token") accessToken: String?
    ): Call<CompanyList>

    @GET("enterprises")
    fun findCompanies2(
        @Header("uid") uid: String?,
        @Header("client") client: String?,
        @Header("access-token") accessToken: String?
    ):Call<CompanyList>

}