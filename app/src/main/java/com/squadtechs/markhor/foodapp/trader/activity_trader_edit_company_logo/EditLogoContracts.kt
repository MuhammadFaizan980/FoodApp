package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_company_logo

import android.net.Uri

interface EditLogoContracts {
    interface IModel {
        fun validate(): Boolean
    }

    interface IPresenter {
        fun initValidation(coordinates: String?, logoUri: Uri?)
    }

    interface IView {
        fun onValidationResult(status: Boolean)
    }
}