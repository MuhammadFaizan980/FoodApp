package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_non_food_company_details_first_screen

interface EditNonFoodContracts {
    interface IModel {
        fun validate(): Boolean
    }

    interface IPresenter {
        fun initValidation(
            companyName: String,
            companyPhone: String,
            cuisine: String,
            companyDescription: String
        )
    }

    interface IView {
        fun onValidationResult(status: Boolean)
    }
}