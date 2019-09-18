package com.squadtechs.markhor.foodapp.trader.activity_trader_change_password

interface TraderChangePasswordMainContracts {
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