package com.brunadev.empresas_android_ioasys.home.data

import com.brunadev.empresas_android_ioasys.mydata.model.CompanyList

interface CompanyCallBack {

    fun onSuccess(response: CompanyList?)

    fun onError(response: String)

    fun onComplete()
}