package com.squadtechs.markhor.foodapp.activity_electronic_license

import android.net.Uri

interface ElectronicLicenseContracts {
    interface IModel {
        fun validate(): Boolean
    }

    interface IPresenter {
        fun initValidation(uri1: Uri?, uri2: Uri?, uri3: Uri?)
        fun saveLicense()
    }

    interface IView {
        fun onValidationResult(status: Boolean)
        fun onSaveLicenseResult(status: Boolean)
    }
}