package com.brunadev.empresas_android_ioasys.login.data

import com.brunadev.empresas_android_ioasys.data.api.api.HTTPClient
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
                        val uid = response.headers()["uid"].orEmpty()
                        val client = response.headers()["client"].orEmpty()
                        val accessToken = response.headers()["access-token"].orEmpty()

                        callback.onSuccess(uid, client, accessToken)
                        callback.onComplete()
                    } else {
                        callback.onError("Login ou senha inválidos")
                        callback.onComplete()
                    }

                }
            })
    }
}