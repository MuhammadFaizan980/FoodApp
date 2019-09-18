package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_profile

class TraderEditProfileModel(
    private val firstName: String,
    private val lastName: String,
    private val phone: String
) : TraderEditProfileMainContracts.IModel {
    override fun validate(): Boolean =
        !firstName.equals("") && !lastName.equals("") && !phone.equals("")
}