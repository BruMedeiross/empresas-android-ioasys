package com.brunadev.empresas_android_ioasys.data.api.api

import android.util.Base64
import com.brunadev.empresas_android_ioasys.mydata.Api
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HTTPClient {

    private const val BASE_URL= "https://empresas.ioasys.com.br/api/v1/"

    private val AUTH = "Basic "+ Base64.encodeToString("testeapple@ioasys.com.br: 12341234".toByteArray(),Base64.NO_WRAP)

    private val okHttpClient = OkHttpClient.Builder().addInterceptor { chain->
        val original = chain.request()

        val requestBuilder = original.newBuilder()
            .addHeader("Authorization", AUTH)
            .method(original.method, original.body)

        val request = requestBuilder.build()
        chain.proceed(request)
    }.build()

    val instance: Api by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

           retrofit.create(Api::class.java)
    }

}