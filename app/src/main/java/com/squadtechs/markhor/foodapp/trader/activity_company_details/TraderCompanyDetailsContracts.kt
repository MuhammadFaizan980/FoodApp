package com.squadtechs.markhor.foodapp.trader.activity_company_details

import android.net.Uri

interface TraderCompanyDetailsContracts {
    interface IView {
        fun onValidationResult(valid: Boolean)
        fun onAddCompanyDetailsResult(status: Boolean)
    }

    interface IModel {
        fun validate(): Boolean
    }

    interface IPresenter {
        fun initValidation(
            companyName: String,
            cuisine: String,
            companyDescription: String,
            uri: Uri?
        )

        fun addCompanyDetails()
    }

}