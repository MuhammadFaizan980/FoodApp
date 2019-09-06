package com.squadtechs.markhor.foodapp.trader.activity_company_timings

interface CompanyTimingsContracts {
    interface IModel {
        fun validate(): Boolean
    }

    interface IView {
        fun onValidationResult(status: Boolean)
        fun onAddCompanyTimingsResult(status: Boolean)
    }

    interface IPresenter {
        fun initValidation(
            sundayOpen: String,
            sundayClose: String,
            mondayOpen: String,
            mondayClose: String,
            tuesdayOpen: String,
            tuesdayClose: String,
            wednesdayOpen: String,
            wednesdayClose: String,
            thursdayOpen: String,
            thursdayClose: String,
            fridayOpen: String,
            fridayClose: String,
            saturdayOpen: String,
            saturdayClose: String,
            location: String?
        )

        fun addCompanyTimings()
    }
}