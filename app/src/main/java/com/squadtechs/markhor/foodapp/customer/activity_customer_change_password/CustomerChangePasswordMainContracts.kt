package com.squadtechs.markhor.foodapp.customer.activity_customer_change_password

interface CustomerChangePasswordMainContracts {
    interface IModel {
        fun validate(): Boolean
    }

    interface IPresenter {
        fun initValidation(
            currentPassword: String,
            savedCurrentPassword: String,
            newPassword: String,
            confirmNewPassword: String
        )

        fun changePassword()
    }

    interface IView {
        fun onValidationResult(status: Boolean)
        fun onChangePasswordResult(status: Boolean)
    }
}