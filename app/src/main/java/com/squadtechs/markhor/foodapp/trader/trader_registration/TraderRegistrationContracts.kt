package com.squadtechs.markhor.foodapp.trader.trader_registration

interface TraderRegistrationContracts {
    interface IModel {
        fun validate(): Boolean
    }

    interface IPresenter {
        fun initValidation(
            firstName: String, lastName: String, mobileNumber: String, companyMobile: String, email: String,
            password: String, confirmPassword: String, agreement: Boolean
        )

        fun initRegistration()
    }

    interface IView {
        fun onValidationResult(status: Boolean)
        fun onRegistrationResult(status: Boolean, message: String)
    }
}