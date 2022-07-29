package com.brunadev.empresas_android_ioasys.presenter

import com.brunadev.empresas_android_ioasys.mydata.LoginCallback
import com.brunadev.empresas_android_ioasys.mydata.LoginRemoteDataSource
import com.brunadev.empresas_android_ioasys.view.LoginFragment

class LoginPresenter(
    private val view: LoginFragment,
    private val dataSource: LoginRemoteDataSource = LoginRemoteDataSource()
) : LoginCallback {

    fun doLogin(email: String, password: String) {
        view.showProgress()
        dataSource.doLoginRequest(email, password, this)
    }

    override fun onSuccess(response: String) {
        view.goToNextScreen()
    }


    override fun onError(response: String) {
        view.hideProgress()
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgress()
    }

}