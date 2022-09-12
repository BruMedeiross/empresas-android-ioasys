package com.brunadev.empresas_android_ioasys.home.presenter

import com.brunadev.empresas_android_ioasys.mydata.model.CompanyList
import com.brunadev.empresas_android_ioasys.home.data.CompanyCallBack
import com.brunadev.empresas_android_ioasys.home.data.CompanyRemoteDataSource
import com.brunadev.empresas_android_ioasys.home.view.HomeFragment

class HomePresenter (
    private val view: HomeFragment,
    private val dataSource: CompanyRemoteDataSource = CompanyRemoteDataSource()
) : CompanyCallBack {

    fun findAllCompanies(email: String, password: String) {
       view.showProgress()
       dataSource.findAllCompanies(email, password, this)
    }

    override fun onSuccess(response: CompanyList?) {
        val companies = response?.enterprises
        if (companies != null) {
            view.showCompanies(companies)
        }
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgress()
    }

}