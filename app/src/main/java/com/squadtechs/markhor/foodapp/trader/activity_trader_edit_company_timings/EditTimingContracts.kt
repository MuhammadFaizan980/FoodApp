package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_company_timings

interface EditTimingContracts {
    interface IModel {
        fun validate(): Boolean
    }

    interface IView {
        fun onValidationResult(status: Boolean)
        fun onEditCompanyTimingsResult(status: Boolean)
        fun onEditCompanyInformationResult(status: Boolean)
        fun onTimePickerResult(timeValue: String, key: String)
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
            saturdayClose: String
        )

        fun editCompanyInformation(
            companyName: String,
            companyDescription: String,
            companyCuisine: String,
            companyPhone: String,
            companyDeliveryTime: String,
            companyDeliveryRange: String,
            companyDeliveryPickUpInfo: String,
            companyCoordinates: String,
            companyDeliveryType: String
        )

        fun editCompanyTimings()
        fun initTimePicker(key: String)
    }

}