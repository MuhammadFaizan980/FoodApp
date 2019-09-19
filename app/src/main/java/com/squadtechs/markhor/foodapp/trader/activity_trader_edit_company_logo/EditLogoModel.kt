package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_company_logo

import android.net.Uri

class EditLogoModel(private val coordinates: String?, private val uri: Uri?) :
    EditLogoContracts.IModel {
    override fun validate(): Boolean = uri != null && coordinates != null
}