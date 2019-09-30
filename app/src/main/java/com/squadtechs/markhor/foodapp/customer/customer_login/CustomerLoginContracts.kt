package com.squadtechs.markhor.foodapp.customer.customer_login

interface CustomerLoginContracts {
    interface IView {
        fun onValidationResult(status: Boolean, message: String)
        fun onLoginResult(status: Boolean, message: String)
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