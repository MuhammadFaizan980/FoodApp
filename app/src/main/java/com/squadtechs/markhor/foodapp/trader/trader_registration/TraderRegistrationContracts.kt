package com.squadtechs.markhor.foodapp.trader.trader_registration

interface TraderRegistrationContracts {
    interface IModel {
        fun validateFields()
    }

    interface IPresenter {
        fun validationCallback(status: Boolean, message: String)
        fun initValidation(
            firstName: String,
            lastName: String,
            mobileNumber: String,
            companyMobile: String,
            email: String,
            password: String,
            confirmPassword: String,
            agreement: Boolean
        )

        fun initRegistration()
    }

    interface IView {
        fun onValidationResult(status: Boolean, message: String)
        fun onRegistrationResult(status: Boolean, message: String)
    }
}