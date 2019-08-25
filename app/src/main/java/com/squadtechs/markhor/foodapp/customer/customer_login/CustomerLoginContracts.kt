package com.squadtechs.markhor.foodapp.customer.customer_login

interface CustomerLoginContracts {
    interface IView {
        fun onValidationResult(status: Boolean)
        fun onLoginResult(status: Boolean)
    }

    interface IModel {
        fun validateFields(): Boolean
    }

    interface IPresenter {
        fun initValidation(email: String, password: String)
        fun initLogin()
    }
}