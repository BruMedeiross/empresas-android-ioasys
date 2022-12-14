package com.brunadev.empresas_android_ioasys.home.data

import com.brunadev.empresas_android_ioasys.common.model.Company
import com.brunadev.empresas_android_ioasys.common.model.CompanyList

interface CompanyCallBack {

    fun onSuccess(response: CompanyList?)

    fun onSearchSuccess(response: List<Company>?)

    fun onError(response: String)

    fun onComplete()
}