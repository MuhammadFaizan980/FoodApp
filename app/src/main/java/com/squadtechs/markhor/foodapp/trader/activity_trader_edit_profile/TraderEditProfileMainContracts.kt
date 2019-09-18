package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_profile

interface TraderEditProfileMainContracts {
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