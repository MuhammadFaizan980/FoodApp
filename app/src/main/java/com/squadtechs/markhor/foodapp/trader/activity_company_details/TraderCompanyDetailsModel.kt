package com.squadtechs.markhor.foodapp.trader.activity_company_details

import android.net.Uri

class TraderCompanyDetailsModel(
    private val companyName: String,
    private val cuisine: String,
    private val companyDescription: String,
    private val uri: Uri?
) : TraderCompanyDetailsContracts.IModel {
    override fun validate(): Boolean {
        return !companyName.equals("") && !companyDescription.equals("") && !cuisine.equals("") && uri != null
    }
}