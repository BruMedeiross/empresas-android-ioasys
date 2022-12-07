package com.brunadev.empresas_android_ioasys.common.api

import com.brunadev.empresas_android_ioasys.common.model.CompanyList
import com.brunadev.empresas_android_ioasys.login.data.LoginResponse
import retrofit2.Call
import retrofit2.http.*


interface Api {

    //Dados codificados em formulário são enviados quando @FormUrlEncodedestão presentes no método.
    @FormUrlEncoded
    @POST("users/auth/sign_in")
    fun userLogin(
        //Cada par chave-valor é anotado @Field contendo o nome e o objeto que fornece o valor.
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

   @GET("enterprises")
   // método de solicitação GET e a URL relativa "ENTERPRISES"
    fun findCompanies(
       // cabeçalhos estáticos para um método usando a @Headers
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