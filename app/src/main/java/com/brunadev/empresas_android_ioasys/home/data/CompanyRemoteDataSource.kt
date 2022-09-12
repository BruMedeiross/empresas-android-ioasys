package com.brunadev.empresas_android_ioasys.home.data

import com.brunadev.empresas_android_ioasys.data.api.api.HTTPClient
import com.brunadev.empresas_android_ioasys.common.model.CompanyList
import com.brunadev.empresas_android_ioasys.login.data.LoginResponse
import com.brunadev.empresas_android_ioasys.home.presenter.HomePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompanyRemoteDataSource {

    fun findAllCompanies(email: String, password: String, callback: HomePresenter) {

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

                        HTTPClient.instance.findCompanies(uid, client, accessToken)
                            .enqueue(object : Callback<CompanyList> {

                                override fun onFailure(call: Call<CompanyList>, t: Throwable) {
                                    callback.onError("Erro Servidor")
                                    callback.onComplete()
                                }

                                override fun onResponse(
                                    call: Call<CompanyList>,
                                    response: Response<CompanyList>
                                ) {
                                    if (response.isSuccessful) {
                                        callback.onSuccess(response.body())
                                        callback.onComplete()
                                    } else {
                                        callback.onError("Erro acesso api")
                                        callback.onComplete()
                                    }
                                }
                            })
                    }else{
                        callback.onError("Erro acesso api")
                        callback.onComplete()
                    }
                }
            })
    }

}