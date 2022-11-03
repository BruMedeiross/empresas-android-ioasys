package com.brunadev.empresas_android_ioasys.home.data

import com.brunadev.empresas_android_ioasys.data.api.api.HTTPClient
import com.brunadev.empresas_android_ioasys.common.model.CompanyList
import com.brunadev.empresas_android_ioasys.home.presenter.HomePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompanyRemoteDataSource {

    fun listCompanies(uid: String, client: String, accessToken: String, callback: HomePresenter) {
        HTTPClient.instance.findCompanies(uid, client, accessToken)
            .enqueue(object : Callback<CompanyList> {

                override fun onFailure(call: Call<CompanyList>, t: Throwable) {
                    callback.onError("Erro Servidor")
                    callback.onComplete()
                }

                override fun onResponse(
                    call: Call<CompanyList>,
                    response: Response<CompanyList>
                ) = if (response.isSuccessful) {
                    callback.onSuccess(response.body())
                    callback.onComplete()
                } else {
                    callback.onError("Erro acesso api")
                    callback.onComplete()
                }
            })
    }

    fun findCompanies(
        uid: String,
        client: String,
        accessToken: String,
        newText: String,
        callback: HomePresenter
    ) = HTTPClient.instance.findCompanies(uid, client, accessToken)
        .enqueue(object : Callback<CompanyList> {

            override fun onFailure(call: Call<CompanyList>, t: Throwable) {
                callback.onError("Erro Servidor")
                callback.onComplete()
            }

            override fun onResponse(
                call: Call<CompanyList>,
                response: Response<CompanyList>
            ) = if (response.isSuccessful) {
                filter(response, newText, callback)
            } else {
                callback.onError("Erro acesso api")
                callback.onComplete()
            }
        })

    private fun filter(response: Response<CompanyList>, newText: String, callback: HomePresenter) {

        val search = response.body()?.enterprises?.filter {
            it.name?.lowercase()?.contains(newText) ?: false
                    || it.type?.enterpriseTypeName.lowercase().contains(newText)
                    || it.country?.lowercase()?.contains(newText) ?: false
        }

        if (search?.isNotEmpty() == true) {
            callback.onSearchSuccess(search)
            callback.onComplete()
        }
    }

}


