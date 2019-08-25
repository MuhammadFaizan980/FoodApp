package com.squadtechs.markhor.foodapp.customer.customer_signup

interface CustomerRegistrationContracts {
    interface IModel {
        fun validate(): Boolean
    }

    interface IPresenter {
        fun initValidation(
            firstName: String, lastName: String, mobileNumber: String, birthday: String, email: String,
            password: String, confirmPassword: String, userAgreement: Boolean
        )

        fun initRegistration()
    }

    interface IView {
        fun onValidationResult(status: Boolean)
        fun onRegistrationResult(status: Boolean)
    }

}