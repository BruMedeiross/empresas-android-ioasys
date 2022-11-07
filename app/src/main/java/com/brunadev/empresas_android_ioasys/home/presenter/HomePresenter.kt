package com.brunadev.empresas_android_ioasys.home.presenter

import com.brunadev.empresas_android_ioasys.common.model.Company
import com.brunadev.empresas_android_ioasys.common.model.CompanyList
import com.brunadev.empresas_android_ioasys.home.data.CompanyCallBack
import com.brunadev.empresas_android_ioasys.home.data.CompanyRemoteDataSource
import com.brunadev.empresas_android_ioasys.home.view.HomeFragment

class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: CompanyRemoteDataSource = CompanyRemoteDataSource()
) : CompanyCallBack {

    fun findCompanies(uid: String, client: String, accessToken: String, newText: String?) {
        view.showProgress()
        dataSource.listCompanies(uid, client, accessToken, newText, this)
    }

    fun searchCompanies(uid: String, client: String, accessToken: String, newText: String?) {
        view.showSearchProgress()
        dataSource.listCompanies(uid, client, accessToken, newText, this)
    }

    override fun onSuccess(response: CompanyList?) {
        val companies = response?.enterprises
        if (companies != null) {
            view.showCompanies(companies)
        }
    }

    override fun onSearchSuccess(response: List<Company>?) {
        response?.let {
            view.showCompanies(response)
        }
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgress()
    }

}