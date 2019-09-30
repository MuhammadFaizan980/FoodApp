package com.squadtechs.markhor.foodapp.trader.trader_login

interface TraderLoginContracts {
    interface IView {
        fun onValidationResult(status: Boolean, message: String)
        fun onLoginResult(status: Boolean, approval: String, profileStatus: String, message: String)
    }

    interface IModel {
        fun validateFields()
    }

    interface IPresenter {
        fun initValidation(email: String, password: String)
        fun validationCallback(status: Boolean, message: String)
        fun initLogin()
    }
}