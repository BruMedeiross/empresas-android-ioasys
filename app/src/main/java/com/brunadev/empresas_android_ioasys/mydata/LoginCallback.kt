package com.brunadev.empresas_android_ioasys.mydata

interface LoginCallback {

    fun onSuccess(response: String)

    fun onError(response: String)

    fun onComplete()
}