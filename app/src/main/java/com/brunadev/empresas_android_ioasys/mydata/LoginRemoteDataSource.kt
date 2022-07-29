package com.brunadev.empresas_android_ioasys.mydata

import com.brunadev.empresas_android_ioasys.data.api.api.HTTPClient
import com.brunadev.empresas_android_ioasys.mydata.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRemoteDataSource {

    fun doLoginRequest(email: String, password: String, callback: LoginCallback) {
        HTTPClient.instance.userLogin(email, password)
            .enqueue(object : Callback<LoginResponse> {

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    callback.onError("Erro ao acessar servidor")
                    callback.onComplete()
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        callback.onSuccess(response.message())
                        callback.onComplete()
                    } else {
                        callback.onError("Login ou senha inválidos")
                        callback.onComplete()
                    }

                }
            })
    }
}