package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_food_company_details_first_screen

interface TraderEditFoodCompanyFirstScreenMainContracts {
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