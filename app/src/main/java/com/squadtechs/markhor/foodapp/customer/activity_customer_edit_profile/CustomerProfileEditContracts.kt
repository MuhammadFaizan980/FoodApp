package com.squadtechs.markhor.foodapp.customer.activity_customer_edit_profile

interface CustomerProfileEditContracts {
    interface IModel {
        fun validate(): Boolean
    }

    interface IPresenter {
        fun initValidation(firstName: String, lastName: String, phone: String)
        fun editProfile()
    }

    interface IView {
        fun onValidationResult(status: Boolean)
        fun onEditProfileResult(status: Boolean)
    }
}