package com.squadtechs.markhor.foodapp.trader.trader_login

interface TraderLoginContracts {
    interface IView {
        fun onValidationResult(status: Boolean)
        fun onLoginResult(status: Boolean, approval: String, message: String)
    }

    interface IModel {
        fun validateFields(): Boolean
    }

    interface IPresenter {
        fun initValidation(email: String, password: String)
        fun initLogin()
    }
}