package com.brunadev.empresas_android_ioasys.login.data

interface LoginCallback {

    fun onSuccess(response: String, client: String, accessToken: String)

    fun onError(response: String)

    fun onComplete()
}