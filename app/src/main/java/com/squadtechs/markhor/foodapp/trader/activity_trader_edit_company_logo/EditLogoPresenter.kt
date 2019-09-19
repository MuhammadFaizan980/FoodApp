package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_company_logo

import android.net.Uri

class EditLogoPresenter(private val mView: EditLogoContracts.IView) : EditLogoContracts.IPresenter {
    private lateinit var mModel: EditLogoContracts.IModel
    override fun initValidation(coordinates: String?, logoUri: Uri?) {
        mModel = EditLogoModel(coordinates, logoUri)
        mView.onValidationResult(mModel.validate())
    }
}